package com.fabio.settings;

/**
 * @author Fabio Rizzello
 * 
 *         General constants to make the project more configurable
 */

public class Settings {

	public static final String ENCODING = "US-ASCII"; // encoding for streams of
														// server and client
	public static final String LOGFILE = "logfile.txt";
	public static final int CARRIAGE_RETURN = 13;
	public static final int NO_CONNECTION = -1;

	public static final String DATABASE_USERNAME = "SA";
	public static final String DATABASE_PASSWORD = "";
	public static final String DATABASE_NAME = "my_database";
	public static final String DATABASE_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
	public static final String DATABASE_URL = "jdbc:hsqldb:";
	public static final int SERVER_PORT = 19999;
	public static final boolean VERBOSE_SERVER = true; // get log file lines
														// printed on screen
	public static final int MAX_SERVER_DELAY_MSEC = 200; // delay used for
															// server
	// how many transaction a server stores in memory before transferring them
	// to DB.
	public static final int TRANSACTIONS_NUMBER_BEFORE_COMMIT = 5;
	public static final String SEPARATOR = ";"; // separator used in logs and
												// communications
	/* CLIENT BEHAVIOUR */
	// how much a client can win or lose, according to their actual balance
	public static final String CLIENT_CONNECT_ADDRESS = "localhost";
	public static final int CLIENT_CONNECT_PORT = 19999;
	public static final int CLIENT_MAX_AMOUNT_TO_WIN = 30;
	public static final int CLIENT_MAX_AMOUNT_TO_LOSE = 15;
	public static final int CLIENT_TRANSACTIONS = 15;
	public static final int CLIENT_TOP_UP_AMOUNT = 100;
	public static final boolean CLIENT_MIGHT_RESEND = true;
	public static final int CLIENT_CHANCE_TO_RESEND = 5; // given n, will be 1/n. example 5 -> 1/5 = 20%
	public static final int MAX_CLIENT_DELAY_MSEC = 200; // delay used for
															// client
	// these ones are used as acronyms in log files
	public static final String THREAD_CREATION_LOG = "ON ";
	public static final String THREAD_END_LOG = "OFF";
	public static final String THREAD_IN_LOG = "IN ";
	public static final String THREAD_OUT_LOG = "OUT";
	// Error Codes
	public static final int ERROR_CODE_SUCCESS = 0;
	public static final int ERROR_CODE_BLACKLIST = 11;
	public static final int ERROR_CODE_RESEND = 22;
	public static final int ERROR_CODE_INSUFFICIENT_BALANCE = 33;
	// delay for periodical blacklist update
	public static final int BLACKLIST_CHECK_MSEC = 10000;

	// avoids inserting SQL commands in parametrized queries
	public static String sanitize(String value) {
		return value.replaceAll("[^A-Za-z0-9]", "");
	}

}
