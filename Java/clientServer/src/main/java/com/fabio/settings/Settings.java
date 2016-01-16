package com.fabio.settings;

/**
@author Fabio Rizzello
General constants to make the project more configurable
 */

public class Settings {
	
	public static final String ENCODING = "US-ASCII";			// encoding for streams of server and client
	public static final String LOGFILE = "logfile.txt";			
	public static final int CARRIAGE_RETURN = 13;		
	public static final int NO_CONNECTION = -1;
	
	public static final String DATABASE_NAME = "my_database";
	public static final int SERVER_PORT = 19999;
	public static final boolean VERBOSE_SERVER = true;				// get log file lines printed on screen 
	public static final int MAX_SERVER_DELAY_MSEC = 1000;				// delay used for server queries
	public static final int CLIENT_TRANSACTIONS = 15;
	public static final String SEPARATOR = ";";						// separator used in logs and communications
	// these ones are used as acronyms in log files
	public static final String THREAD_CREATION_LOG = "ON ";			
	public static final String THREAD_END_LOG = "OFF";				
	public static final String THREAD_IN_LOG = "IN ";
	public static final String THREAD_OUT_LOG = "OUT";
	
	// Using a combination of host, port and timestamp for transaction IDs
	public static String transactionize(String host, int port, String Timestamp){
		return host+port+Timestamp.replace(" ", "").replace(":", "").replace(";", "");
	}

}
