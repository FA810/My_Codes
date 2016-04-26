package com.fabio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class RedisJava {

	// An example with a List
	public void setListExample() {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		// Store data in redis list
		System.out.println("list push");
		jedis.lpush("list", "item1");
		jedis.lpush("list", "item2");
		jedis.lpush("list", "item3");
		// Get the stored data and print it
		System.out.println("list print");
		List<String> list = jedis.lrange("list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Stored string in redis:: " + list.get(i));
		}
	}

	/*
	 * How to take a Message object and transform it into a JSON object and
	 * viceversa
	 */
	public void jsonizeMessageExample() {
		String message = "{\r\n\t" + "\"msgid\": \"api3309uwpeojfwojfpwoejfwefafsdgsdgf\",\r\n\t"
				+ "\"spid\": \"api.390.20160328\",\r\n\t" + "\"account_id\": 390,\r\n\t"
				+ "\"insert_time\": \"2016-03-28 16:02:05.528\",\r\n\t" + "\"scheduled_send_time\": null,\r\n\t"
				+ "\"type\": \"mt_sms\",\r\n\t" + "\"mt_sms\": {\r\n\t\t" + "\"from\": \"CoffeeShop\",\r\n\t\t"
				+ "\"to\": \"+37251916062\",\r\n\t\t" + "\"text\": \"Message content in UTF8 encoding\",\r\n\t\t"
				+ "\"udh\": null,\r\n\t\t" + "\"coding\": 0,\r\n\t\t" + "\"charset\": \"UTF-8\",\r\n\t\t"
				+ "\"parts\": 1\r\n\t},\r\n\t" + "\"hlr\": null,\r\n\t" + "\"dlr\": null,\r\n\t"
				+ "\"routing\": {\r\n\t\t" + "\"cost\": 0.0550,\r\n\t\t" + "\"route_id\": 3\r\n\t},\r\n\t"
				+ "\"pricing\": null,\r\n\t" + "\"meta_data\": {\r\n\t\t"
				+ "\"from_queue\": \"filter:before:spam:3\",\r\n\t\t" + "\"to_queue\": \"filter:after:spam:3\",\r\n\t\t"
				+ "\"priority\": 3\r\n\t}\r\n}";

		Gson g = new Gson();

		Message m1 = g.fromJson(message, Message.class);

		System.out.println(m1.account_id); // single field
		System.out.println(g.toJson(m1)); // whole json
	}

	/*
	 * Get difference in seconds between two dates. Example:
	 * getTimeDifferenceinSeconds("2016-04-19 17:15:09.020",
	 * "2016-04-19 17:17:39.040"); result: 150
	 */
	public static long getTimeDifferenceinSeconds(String time1, String time2) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Date date1 = formatter.parse(time1);
		Date date2 = formatter.parse(time2);

		// Get msec from each, and subtract
		long diff = date2.getTime() - date1.getTime();

		return diff / 1000;
	}

	// Print a Redis list given the name
	public static void printList(String listName) {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		// Get the stored data and print it
		List<String> list = jedis.lrange(listName, 0, jedis.llen(listName));
		System.out.println("List :: " + listName);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("" + list.get(i));
		}
	}

	// Get the first element of a Redis list without popping it.
	public static Message getFirstMessageWithoutRemoving(String key, Jedis jedis) {
		long size = jedis.llen(key);
		if (size == 0)
			return null;
		return new Gson().fromJson(jedis.lrange(key, 0, 1).get(0), Message.class);
	}

	// Get the last element of a Redis list without removing it.
	public static Message getLastMessageWithoutRemoving(String key, Jedis jedis) {
		long size = jedis.llen(key);
		if (size == 0)
			return null;
		return new Gson().fromJson(jedis.lrange(key, size - 1, size).get(0), Message.class);
	}

	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		return sdf.format(now);
	}

	public static void main(String[] args) throws InterruptedException, ParseException {

		String message = "{\r\n\t" + "\"msgid\": \"api3309uwpeojfwojfpwoejfwefafsdgsdgf\",\r\n\t"
				+ "\"spid\": \"api.390.20160328\",\r\n\t" + "\"account_id\": 390,\r\n\t"
				+ "\"insert_time\": \"2016-03-28 16:02:05.528\",\r\n\t" + "\"scheduled_send_time\": null,\r\n\t"
				+ "\"type\": \"mt_sms\",\r\n\t" + "\"mt_sms\": {\r\n\t\t" + "\"from\": \"CoffeeShop\",\r\n\t\t"
				+ "\"to\": \"+37251916062\",\r\n\t\t" + "\"text\": \"Message content in UTF8 encoding\",\r\n\t\t"
				+ "\"udh\": null,\r\n\t\t" + "\"coding\": 0,\r\n\t\t" + "\"charset\": \"UTF-8\",\r\n\t\t"
				+ "\"parts\": 1\r\n\t},\r\n\t" + "\"hlr\": null,\r\n\t" + "\"dlr\": null,\r\n\t"
				+ "\"routing\": {\r\n\t\t" + "\"cost\": 0.0550,\r\n\t\t" + "\"route_id\": 3\r\n\t},\r\n\t"
				+ "\"pricing\": null,\r\n\t" + "\"meta_data\": {\r\n\t\t"
				+ "\"from_queue\": \"filter:before:spam:3\",\r\n\t\t" + "\"to_queue\": \"filter:after:spam:3\",\r\n\t\t"
				+ "\"priority\": 3\r\n\t}\r\n}";

		int ARBITRARY_SECONDS_DELAY = 2;
		int ARBITRARY_MILLISECONDS_OFFSET = 1;
		int WAITING_QUEUE_SIZE = 5;

		Jedis jedis = new Jedis("localhost");
		// clear Redis database
		jedis.flushDB();

		Gson g = new Gson();

		// generating fake messages to be added to queue
		FakeMessageGenerator fmg = new FakeMessageGenerator();
		for (int i = 0; i < 12; i++) {
			Message generated = fmg.generateMessage();
			// System.out.println(g.toJson(generated));
			jedis.rpush("main", g.toJson(generated));
		}

		printList("main");

		/*
		 * REMEMBER: lpop = take first rpush = append last
		 */

		long size = jedis.llen("main");
		for (int i = 0; i < size; i++) {
			Message m2 = getFirstMessageWithoutRemoving("main", jedis);
			System.out.println("considering message: " + m2.msgid);
			Thread.sleep((ARBITRARY_SECONDS_DELAY * 1000) + ARBITRARY_MILLISECONDS_OFFSET);
			// if the temporary queue has still space
			if (jedis.llen("timed") < WAITING_QUEUE_SIZE) {
				jedis.lpop("main");
				jedis.rpush("timed", g.toJson(m2));
			} else {
				// there is no space so I need to remove oldest or reject
				// message
				Message old = getFirstMessageWithoutRemoving("timed", jedis);

				String currentTime = getCurrentTime();
				if (getTimeDifferenceinSeconds(old.insert_time, currentTime) > ARBITRARY_SECONDS_DELAY) {
					// the new message has been generated in the right time,
					// hence it can be enqueued and replace the oldest
					jedis.lpop("timed");
					jedis.rpush("sent", g.toJson(old));
					jedis.lpop("main");
					jedis.rpush("timed", g.toJson(m2));
				} else {
					// the new message has been generated too recently therefore
					// it's spam and gotta be rejected
					jedis.lpop("main");
					jedis.rpush("rejected", g.toJson(m2));
				}

			}
		}

		// print all lists
		printList("timed");
		printList("main");
		printList("sent");
		printList("rejected");

	}
}