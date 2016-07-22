package com.fabio.connectors;

import java.net.*;
import java.util.Random;
import java.io.*;

import com.fabio.exceptions.BlackListException;
import com.fabio.settings.Settings;

/**
 * @author Fabio Rizzello
 * 
 *         SocketClient class is a simple example of a TCP/IP Socket Client.
 */
public class SocketClient {
	static Random random = new Random();

	/** Function to create randomly a balance change. */
	private static int changeBalance(int myActualBalance) {
		if (myActualBalance == 0) {
			return Settings.CLIENT_TOP_UP_AMOUNT;
		}
		int randomNumber = (random.nextInt(Math.min(Settings.CLIENT_MAX_AMOUNT_TO_WIN, myActualBalance))
				- Math.min(Settings.CLIENT_MAX_AMOUNT_TO_LOSE, myActualBalance));
		return randomNumber;
	}

	public static void main(String[] args) {
		int myActualBalance = 0;
		int amountToAdd = myActualBalance;

		Socket connection = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedInputStream bufferedInputStream = null;
		InputStreamReader inputStreamReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		String username = "user";
		// if no username is passed will use default
		if (args.length > 0) {
			username = args[0];
		}
		int transactionsNumber = 0;
		System.out.println("SocketClient initialized");
		int character;
		try {
			// obtain an address from the server and establish a socket
			// connection
			InetAddress address = InetAddress.getByName(Settings.CLIENT_CONNECT_ADDRESS);
			connection = new Socket(address, Settings.CLIENT_CONNECT_PORT);

			// setting up streams
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			outputStreamWriter = new OutputStreamWriter(bos, Settings.ENCODING);
			
			// until the connection is still on
			while (transactionsNumber < Settings.CLIENT_TRANSACTIONS && !connection.isClosed()) {
				
				String messageToServer;

				// simulating randomly  a client who sends the same transaction
				if ((Settings.CLIENT_MIGHT_RESEND) && (random.nextInt(Settings.CLIENT_CHANCE_TO_RESEND) == 0) && (transactionsNumber > 0)) {
					transactionsNumber = transactionsNumber - 1;
					System.out.println("RESENDING");
					messageToServer = username + Settings.SEPARATOR + transactionsNumber + Settings.SEPARATOR + 0
							+ (char) Settings.CARRIAGE_RETURN;
				} else {
					// normal sending
					messageToServer = username + Settings.SEPARATOR + transactionsNumber + Settings.SEPARATOR + amountToAdd
							+ (char) Settings.CARRIAGE_RETURN;
				}

				System.out.println("   [CLIENT]: " + messageToServer);
				// write across the socket connection and flush the buffer
				outputStreamWriter.write(messageToServer);
				outputStreamWriter.flush();

				bufferedInputStream = new BufferedInputStream(connection.getInputStream());
				inputStreamReader = new InputStreamReader(bufferedInputStream, Settings.ENCODING);

				// read the socket's InputStream and append to a StringBuffer
				while ((character = inputStreamReader.read()) != Settings.CARRIAGE_RETURN
						&& character != Settings.NO_CONNECTION && !connection.isClosed())
					stringBuffer.append((char) character);

				System.out.println("[SERVER]: " + stringBuffer);
				String messageFromServer = stringBuffer.substring(0, stringBuffer.length());
				String[] messageParts = messageFromServer.split(Settings.SEPARATOR);
				
				int errorCode = Integer.parseInt(messageFromServer.split(Settings.SEPARATOR)[1]);
				
				if (errorCode == Settings.ERROR_CODE_BLACKLIST) {
					// deal with being in the blacklist
					throw new BlackListException();
				} else {
					// if (errorCode == Settings.ERROR_CODE_SUCCESS || errorCode
					// == Settings.ERROR_CODE_INSUFFICIENT_BALANCE || errorCode
					// == Settings.ERROR_CODE_RESEND) {
					myActualBalance = Integer.parseInt(messageParts[4]);
					amountToAdd = changeBalance(myActualBalance);
				}
				stringBuffer.delete(0, stringBuffer.length());

				transactionsNumber++;
				// add some random delay
				Thread.sleep(random.nextInt(Settings.MAX_CLIENT_DELAY_MSEC));				
			}

		} catch (BlackListException b) {
			System.out.println("BlackList: it seems that your account has been blacklisted");
		} catch (IOException f) {
			System.out.println("IOException: Connection with Server lost");
		} catch (Exception g) {
			System.out.println("Exception: " + g);
		} finally {
			// closing all streams
			try {
				System.out.println("SocketClient closing");
				outputStreamWriter.close();
				inputStreamReader.close();
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
