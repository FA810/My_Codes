package com.fabio.connectors;

import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.fabio.database.DBManager;
import com.fabio.exceptions.BlackListException;
import com.fabio.settings.Settings;

/**
 * @author Fabio Rizzello
 * 
 *         MultipleSocketServer can handle simultaneous connections through the
 *         use of threads.
 */

public class MultipleSocketServer implements Runnable {

	private String timeStamp;
	private static Random random = new Random();
	private static DBManager dbManager;
	private static List<String> blacklist = new ArrayList<String>();
	private static Map<String, UserWallet> activeUsers = new HashMap<String, UserWallet>();
	private static ServerSocket serverSocket = null;
	private Socket connection = null;

	public static void main(String[] args) {
		MultipleSocketServer.runServer();
	}

	public static void runServer() {

		try {
			// dbManager will handle all the queries
			dbManager = new DBManager(Settings.DATABASE_NAME);
			Thread blackListUpdater = new Thread(new BlackListUpdater());
			blackListUpdater.start();
			// initializing the tables we need

			// dbManager.query("DROP TABLE IF EXISTS PLAYER2");
			// dbManager.query("DROP TABLE IF EXISTS BLACKLIST");
			dbManager.query(
					"CREATE TABLE IF NOT EXISTS PLAYER2 ( USERNAME VARCHAR(256) NOT NULL, BALANCE INTEGER, BALANCE_VERSION VARCHAR(80), PRIMARY KEY (USERNAME))");
			dbManager.query(
					"CREATE TABLE IF NOT EXISTS BLACKLIST ( USERNAME VARCHAR(256) NOT NULL, PRIMARY KEY (USERNAME))");
			// dbManager.query("INSERT INTO BLACKLIST(USERNAME)
			// VALUES('blacklisted')");

			// adding some users to blacklist even if they already exist
			insertUserBlacklistDB("blacklisted");
			insertUserBlacklistDB("blacklistedAlways");
			insertUserBlacklistDB("yolo");
			insertUserBlacklistDB("swag");
			insertUserBlacklistDB("justinBieber");
			insertUserBlacklistDB("mileyCyrus");
			
		} catch (SQLException ex2) {
			ex2.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		try {
			// create log file and start write it
			appendContents(Settings.LOGFILE, "# Server started at " + new Date().toString());

			// waiting for clients and creating threads for every connection
			// received
			serverSocket = new ServerSocket(Settings.SERVER_PORT);
			System.out.println("MultipleSocketServer started");
			while (true) {
				updateBlacklist();
				Socket connection = serverSocket.accept();
				Runnable runnable = new MultipleSocketServer(connection, dbManager);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// needed closure of streams, socket and database after use
			appendContents(Settings.LOGFILE, Settings.THREAD_END_LOG);
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
			try {
				dbManager.shutdown();
			} catch (SQLException e) {
			}
		}
	}

	MultipleSocketServer(Socket s, DBManager dbManager) {
		connection = s;
		MultipleSocketServer.dbManager = dbManager;
	}

	/* periodical background process to update blacklist. */
	private static class BlackListUpdater implements Runnable {
		public void run() {
			while (true) {
				System.out.println("---- Blacklist check " + new Date());
				try {
					updateBlacklist();
					Thread.sleep(Settings.BLACKLIST_CHECK_MSEC);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** Function to append relevant events to Log file. */
	public static synchronized void appendContents(String fileName, String content) {
		if (Settings.VERBOSE_SERVER)
			System.out.println(content);
		BufferedWriter logWriter = null;
		try {
			File logFile = new File(fileName);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			if (logFile.canWrite()) {
				logWriter = new BufferedWriter(new FileWriter(fileName, true));
				logWriter.write(content);
				logWriter.newLine();
			}
		} catch (IOException oException) {
			throw new IllegalArgumentException("Error appending/File cannot be written: \n" + fileName);
		} finally {
			try {
				logWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* function to get the blacklist from database. */
	private static synchronized void updateBlacklist() {
		blacklist.clear();
		try {
			String selectBlacklist = "SELECT USERNAME FROM BLACKLIST";
			ResultSet rs = dbManager.queryWithResultSet(selectBlacklist);
			while (rs.next()) {
				blacklist.add(rs.getString("USERNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void updateUserWalletDB(String username) {
		if (activeUsers.containsKey(username)) {
			updateUserWalletDB(activeUsers.get(username));
		}
	}

	/*
	 * insert new information about an active user (who started a session) in
	 * DB.
	 */
	private static synchronized void updateUserWalletDB(UserWallet userWallet) {
		if (activeUsers.containsKey(userWallet.getUsername())) {
			try {
				String timeStamp = new java.util.Date().toString();
				dbManager.update("UPDATE PLAYER2 SET BALANCE=" + userWallet.getBalance() + ", BALANCE_VERSION='"
						+ timeStamp + "' WHERE USERNAME = '" + Settings.sanitize(userWallet.getUsername()) + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/* get information about a user in DB */
	private static synchronized ResultSet selectUserWalletDB(String username) {
		ResultSet rs = null;
		try {
			rs = dbManager
					.queryWithResultSet("SELECT USERNAME, BALANCE, BALANCE_VERSION FROM PLAYER2 WHERE USERNAME = '"
							+ Settings.sanitize(username) + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/* new user to insert in DB blacklist */
	private static synchronized void insertUserBlacklistDB(String username) {
		try {
			blacklist.add(username);
			dbManager.query("MERGE INTO BLACKLIST USING (VALUES('"+ Settings.sanitize(username) +"')) AS vals(x) ON BLACKLIST.USERNAME = vals.x WHEN NOT MATCHED THEN INSERT VALUES vals.x");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* new user to insert in DB */
	private static synchronized void insertUserWalletDB(UserWallet userWallet) {
		try {
			dbManager.update("INSERT INTO PLAYER2(USERNAME,BALANCE,BALANCE_VERSION) " + "VALUES('"
					+ Settings.sanitize(userWallet.getUsername()) + "'," + userWallet.getBalance() + ",'"
					+ userWallet.getBalanceVersion() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Thread life-cycle for each connection. */
	public void run() {

		BufferedOutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedInputStream is = null;
		InputStreamReader isr = null;
		StringBuffer dataBuffer;
		String username = "";
		UserWallet userWallet = new UserWallet();
		try {
			is = new BufferedInputStream(connection.getInputStream());
			isr = new InputStreamReader(is, Settings.ENCODING);
			int character;
			int transactionId = -1;

			// read until client disconnects
			while (!connection.isClosed()) {
				dataBuffer = new StringBuffer();
				while ((character = isr.read()) != Settings.CARRIAGE_RETURN && character != Settings.NO_CONNECTION
						&& !connection.isClosed()) {
					dataBuffer.append((char) character);
				}
				if ((dataBuffer != null) && (dataBuffer.length() > 0)) {

					// just adding some delay
					try {
						Thread.sleep(random.nextInt(Settings.MAX_SERVER_DELAY_MSEC));
					} catch (Exception e) {
					}
					/*
					 * client will be sending message in this format:
					 * [username];[transaction id];[balance change]
					 */
					String messageFromClient = dataBuffer.substring(0, dataBuffer.length());
					String[] messageParts = messageFromClient.split(Settings.SEPARATOR);
					username = messageParts[0];
					// save previous transaction ID and read the new one
					int oldTransactionId = transactionId;
					transactionId = Integer.parseInt(messageParts[1]);
					int amountToAdd = Integer.parseInt(messageParts[2]);
					// log the arrival of a new active user
					if ((transactionId == 0) && (!activeUsers.containsKey(username))) {
						appendContents(Settings.LOGFILE, Settings.THREAD_CREATION_LOG + Settings.SEPARATOR + username);
					}
					appendContents(Settings.LOGFILE,
							Settings.THREAD_IN_LOG + Settings.SEPARATOR + username + Settings.SEPARATOR + dataBuffer);

					// setup for replying
					os = new BufferedOutputStream(connection.getOutputStream());
					osw = new OutputStreamWriter(os, Settings.ENCODING);

					// userWallet is the instance we keep in memory of a User
					// Wallet
					userWallet.setUsername(username);
					userWallet.setLastTransaction(transactionId);
					String messageToClient;
					int errorCode = Settings.ERROR_CODE_SUCCESS;
					if (blacklist.contains(username)) {
						// the player is blacklisted, let's launch a blacklist
						// exception
						timeStamp = new java.util.Date().toString();
						errorCode = Settings.ERROR_CODE_BLACKLIST;
						messageToClient = transactionId + Settings.SEPARATOR + errorCode + Settings.SEPARATOR
								+ timeStamp + Settings.SEPARATOR + 0 + Settings.SEPARATOR + 0 + "";
						osw.write(messageToClient);
						appendContents(Settings.LOGFILE, Settings.THREAD_OUT_LOG + Settings.SEPARATOR + username
								+ Settings.SEPARATOR + messageToClient);
						osw.flush();
						osw.close();
						throw new BlackListException();
					} else {
						// player not blacklisted

						if (!activeUsers.containsKey(username)) {
							ResultSet rs = selectUserWalletDB(username);

							if (rs.next()) {
								// user was in DB
								userWallet.setBalance(rs.getInt("BALANCE"));
								userWallet.setBalanceVersion(rs.getString("BALANCE_VERSION"));
							} else {
								// new user, not in DB
								userWallet.setBalance(0);
								userWallet.setBalanceVersion(new java.util.Date().toString());
								insertUserWalletDB(userWallet);
							}
							// add user and his wallet in memory
							activeUsers.put(username, userWallet);
							rs.close();
						}

						if (oldTransactionId == userWallet.getLastTransaction()) {
							// client have resent previous transaction, send
							// balance unmodified back
							System.out.println("Same transaction ID - resending balance with no change");
							errorCode = Settings.ERROR_CODE_RESEND;
							timeStamp = new java.util.Date().toString();
							messageToClient = transactionId + Settings.SEPARATOR + errorCode + Settings.SEPARATOR
									+ timeStamp + Settings.SEPARATOR + amountToAdd + Settings.SEPARATOR
									+ userWallet.getBalance() + (char) Settings.CARRIAGE_RETURN;

						} else {
							// go on with changing balance with the incoming
							// transaction and update transaction ID
							oldTransactionId = transactionId;
							userWallet.setLastTransaction(transactionId);
							// check if balance is now greater than 0
							int newBalance = userWallet.getBalance() + amountToAdd;
							if (newBalance >= 0) {
								userWallet.setBalance(newBalance);
							} else {
								// just a different error code to provide more
								// fine grained information.
								// this doesn't happen if you make the client
								// checking the balance available and playing
								// accordingly.
								userWallet.setBalance(0);
								errorCode = Settings.ERROR_CODE_INSUFFICIENT_BALANCE;
							}
							// server->client: transaction id, error code,
							// balance
							// version, balance change, balance after change
							timeStamp = new java.util.Date().toString();
							messageToClient = transactionId + Settings.SEPARATOR + errorCode + Settings.SEPARATOR
									+ timeStamp + Settings.SEPARATOR + amountToAdd + Settings.SEPARATOR
									+ userWallet.getBalance() + (char) Settings.CARRIAGE_RETURN;
						}

					}

					// send reply to client
					osw.write(messageToClient);
					osw.flush();
					// if it's time to save to DB, let's do it
					if ((transactionId % Settings.TRANSACTIONS_NUMBER_BEFORE_COMMIT) == 0) {
						updateUserWalletDB(username);
					}
					// append to log file
					appendContents(Settings.LOGFILE, Settings.THREAD_OUT_LOG + Settings.SEPARATOR + username
							+ Settings.SEPARATOR + messageToClient);

				} else {
					throw new SocketException();
				}
			}
		} catch (BlackListException e) {
			System.out.println("user is in blacklist: " + username);
		} catch (SocketException e) {
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// the user has ended his session, write it in log, update his
			// wallet in DB and remove it from active users (in memory)
			appendContents(Settings.LOGFILE, Settings.THREAD_END_LOG + Settings.SEPARATOR + username);
			updateUserWalletDB(username);
			activeUsers.remove(username);
			// close all open streams to release resources
			try {
				osw.close();
				isr.close();
			} catch (IOException e1) {
			}
			try {
				connection.close();
			} catch (IOException e) {
			}
		}
	}
}
