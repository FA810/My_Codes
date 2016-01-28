package com.fabio.connectors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import com.fabio.database.DBManager;
import com.fabio.settings.Settings;

/**
@author Fabio Rizzello
MultipleSocketServer can handle simultaneous connections
through the use of threads.
 */

public class MultipleSocketServer implements Runnable {

	private Socket connection;
	private String timeStamp;
	private static PrintWriter writer;
	private static Random random;
	private static Calendar calendar;
	private static DBManager dbManager;		

	public static void main(String[] args) {
		int port = Settings.SERVER_PORT;
		int threadsCount = 0;
		ServerSocket serveSocket = null;
		calendar = Calendar.getInstance();

		try {
			// dbManager will handle all the queries 
			dbManager = new DBManager(Settings.DATABASE_NAME);
			
			// initializing the tables we need
			try {
				dbManager.query("DROP TABLE IF EXISTS PLAYER");
				dbManager.query("CREATE TABLE PLAYER ( id INTEGER IDENTITY, USERNAME VARCHAR(256), BALANCE INTEGER)");
				dbManager.query("CREATE TABLE IF NOT EXISTS TIMES  ( id INTEGER IDENTITY, USERNAME VARCHAR(256), HOUR INTEGER, MINUTE INTEGER, SECONDS DOUBLE)");
			} catch (SQLException ex2) {}			
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		try {
			// create log file and start write it
			writer = new PrintWriter(Settings.LOGFILE, "UTF-8");
			appendContents(Settings.LOGFILE, "# Log file for server");
			
			serveSocket = new ServerSocket(port);
			System.out.println("MultipleSocketServer starting");
			// waiting for clients and creating threads for every connection received
			while (true) {
				Socket connection = serveSocket.accept();
				Runnable runnable = new MultipleSocketServer(connection, dbManager);
				++threadsCount;
				appendContents(Settings.LOGFILE, Settings.THREAD_CREATION_LOG + Settings.SEPARATOR + "THREAD" + threadsCount);
				Thread thread = new Thread(runnable);
				thread.setName("THREAD" + threadsCount);				
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// needed closure of streams, socket and database after use
			appendContents(Settings.LOGFILE, Settings.THREAD_END_LOG);			
			try {
				writer.close();
				serveSocket.close();
			} catch (IOException e) {
			}
			try {
				// this view creation is for checking all statistics about total queries and their times
				dbManager.query("CREATE VIEW STATISTICS AS SELECT USERNAME,HOUR,MINUTE, MAX(SECONDS) AS MAXSECS, MIN(SECONDS) AS MINSECS, AVG(SECONDS) AS AVGSECS, COUNT(*) AS TOTAL FROM PUBLIC.TIMES GROUP BY USERNAME,HOUR,MINUTE");
				dbManager.shutdown();
			} catch (SQLException e) {
			}
		}
	}
	
	MultipleSocketServer(Socket s, DBManager dbManager) {
		this.connection = s;
		MultipleSocketServer.dbManager = dbManager;
		calendar = Calendar.getInstance();
		random = new Random();
	}
	
	/**  Function used to keep track of every balance change and collect query times. */
	public static synchronized int recordDBTransaction(String username, int balance){
		try {			
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			long start_time = System.currentTimeMillis();			
			try {Thread.sleep(random.nextInt(Settings.MAX_SERVER_DELAY_MSEC));} catch (InterruptedException e) {}			
			// use this next query if you want to record each and every balance change!
			//dbManager.update("INSERT INTO PLAYER(USERNAME,BALANCE) VALUES('"+username+"', '"+balance+"')");
			dbManager.update("UPDATE PLAYER SET balance=(balance+"+balance+") WHERE username='"+username+"'");
			long end_time = System.currentTimeMillis();
			double difference = new BigDecimal(((end_time-start_time)/1000.00)).setScale(2, RoundingMode.HALF_UP).doubleValue();
			// inserting query time just measured
			dbManager.update("INSERT INTO TIMES(USERNAME,HOUR,MINUTE,SECONDS) VALUES('"+username+"',"+hour+","+minute+","+difference+")");
			return 0;
		} catch (SQLException ex3) {
			ex3.printStackTrace();
			return -1;
		}		
	}	
	
	/**  Function to append relevant events to Log file. */
	public static synchronized void appendContents(String fileName, String content) {
		if(Settings.VERBOSE_SERVER)
			System.out.println(content);
		try {

			File ownFile = new File(fileName);
			if (!ownFile.exists()) {
				ownFile.createNewFile();
			}
			if (ownFile.canWrite()) {
				BufferedWriter ownWriter = new BufferedWriter(new FileWriter(
						fileName, true));
				ownWriter.write(content);
				ownWriter.newLine();
				ownWriter.close();
			}
		} catch (IOException oException) {
			throw new IllegalArgumentException("Error appending/File cannot be written: \n" + fileName);
		}
	}

	/**  Thread life-cycle for each connection. */
	public void run() {
		OutputStreamWriter osw = null;
		String thread_name = Thread.currentThread().getName();
		int balance = 0;
		try {
			// creating the new username in the database with zero balance first
			dbManager.update("INSERT INTO PLAYER(USERNAME,BALANCE) VALUES('"+thread_name+"', 0)");
			
			BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
			InputStreamReader isr = new InputStreamReader(is, Settings.ENCODING);			
			int character;
			while (!connection.isClosed()) {
				StringBuffer dataBuffer = new StringBuffer();
				while ((character = isr.read()) != Settings.CARRIAGE_RETURN
						&& character != Settings.NO_CONNECTION
						&& !connection.isClosed()) {
					dataBuffer.append((char) character);
				}
				if ((dataBuffer != null) && (dataBuffer.length() > 0)) {					
					appendContents(Settings.LOGFILE, Settings.THREAD_IN_LOG + Settings.SEPARATOR + thread_name + Settings.SEPARATOR+ dataBuffer);
					// just adding some delay
					try {
						Thread.sleep(random.nextInt(Settings.MAX_SERVER_DELAY_MSEC));
					} catch (Exception e) {}
					String message = dataBuffer.substring(0, dataBuffer.length());
					String[] messageParts = message.split(Settings.SEPARATOR);
					/* 
					 * client will be sending message in this format:
					 * [transaction ID];[its time of creation];[balance change]
					 */
					// amount to be added to balance
					int sum = Integer.parseInt(messageParts[2]);
					String transaction_id = messageParts[0];
					balance += sum;
					int errorCode = recordDBTransaction(thread_name, sum);					
					timeStamp = new java.util.Date().toString();
					// collected all info, we start to send a reply to client
					String returnCode = transaction_id+ Settings.SEPARATOR + timeStamp + Settings.SEPARATOR + errorCode + Settings.SEPARATOR + sum + Settings.SEPARATOR + balance + (char) Settings.CARRIAGE_RETURN;
					BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
					osw = new OutputStreamWriter(os, Settings.ENCODING);
					osw.write(returnCode);
					osw.flush();
					// and append to log file
					appendContents(Settings.LOGFILE, Settings.THREAD_OUT_LOG + Settings.SEPARATOR + thread_name + Settings.SEPARATOR + returnCode);					

				} else {
					throw new SocketException();
				};

			}
		} catch (SocketException e) {
			appendContents(Settings.LOGFILE, Settings.THREAD_END_LOG + Settings.SEPARATOR + thread_name );
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {				
				connection.close();
			} catch (IOException e) {
			}
		}
	}
}
