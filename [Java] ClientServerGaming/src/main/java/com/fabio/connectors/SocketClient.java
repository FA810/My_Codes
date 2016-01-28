package com.fabio.connectors;

import java.net.*;
import java.util.Random;
import java.io.*;

import com.fabio.settings.Settings;

/**
@author Fabio Rizzello
SocketClient class is a simple example of a TCP/IP Socket Client.
 */
public class SocketClient {
	static Random random = new Random();
	
	/** Function to create randomly a balance change. */
	private static int changeBalance(){
		int randomNumber=(random.nextInt(21)-10);
		return randomNumber;
	}
	
	public static void main(String[] args) {
		
		String host = "localhost";
		int port = 19999;
		// we can suppose an initial balance from 200 to 500
		int balance = (random.nextInt(4)+2)*100;
		// we can use this for client creation time
		double ID = System.currentTimeMillis();
		
		Socket connection = null;
		OutputStreamWriter osw = null;
		StringBuffer stringBuffer = new StringBuffer();
		String TimeStamp;
		System.out.println("SocketClient initialized");
		int character;
		try {
			// obtain an address from the server and establish a socket connection
			InetAddress address = InetAddress.getByName(host);
			connection = new Socket(address, port);

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			osw = new OutputStreamWriter(bos, Settings.ENCODING);

			// supposing each clients will get a maximum number of transactions
			int transactionsNumber = 0;
			while (transactionsNumber < Settings.CLIENT_TRANSACTIONS && !connection.isClosed()) {
				// collecting info for transacton ID
				TimeStamp = new java.util.Date().toString();
				String transaction_id = Settings.transactionize(host,port,TimeStamp);
				String process = transaction_id + Settings.SEPARATOR +ID+ Settings.SEPARATOR +balance+ Settings.SEPARATOR+(char) 13;
				System.out.println("SocketClient sending...");
				/** Write across the socket connection and flush the buffer */
				osw.write(process);
				osw.flush();

				BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
				InputStreamReader isr = new InputStreamReader(bis, Settings.ENCODING);

				/** Read the socket's InputStream and append to a StringBuffer */
				
				while ((character = isr.read()) != Settings.CARRIAGE_RETURN
						&& character != Settings.NO_CONNECTION
						&& !connection.isClosed())
					stringBuffer.append((char) character);
				
				System.out.println("[SERVER]:"+stringBuffer);
				String message = stringBuffer.substring(0, stringBuffer.length());
				int errorCode = Integer.parseInt(message.split(Settings.SEPARATOR)[2]);
				// if previous balance update was successful, balance can change again 
				if(errorCode == 0)
					balance = changeBalance();
				stringBuffer.delete(0, stringBuffer.length());
				
				Thread.sleep(random.nextInt(1000));				 
				transactionsNumber++;
			}
			System.out.println("SocketClient closing");
			/** Close the socket connection. */
			connection.close();
			System.out.println(stringBuffer);
		} catch (IOException f) {
			System.out.println("IOException: Connection with Server lost");
		} catch (Exception g) {
			System.out.println("Exception: " + g);
		} finally{
			try {
				osw.close();
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
