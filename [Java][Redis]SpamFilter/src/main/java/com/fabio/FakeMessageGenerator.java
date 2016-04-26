package com.fabio;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FakeMessageGenerator {
	
	static Random rand = new Random();
	String senders[] = new String[]{"A","B","C","D"};
	
	public Message generateMessage(){
		int randomSender = randInt(0, senders.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Message fakeMessage = new Message();
		// unmodified constants...
		fakeMessage.spid = "api.390.20160328";
		fakeMessage.msgid = "api3309u"+randInt(100, 900);
		fakeMessage.scheduled_send_time = null;
		Date now = new Date();
		fakeMessage.insert_time = sdf.format(now);
		fakeMessage.type = "mt_sms";
		fakeMessage.hlr = null;
		fakeMessage.dlr = null;		
		fakeMessage.pricing = null;		
		fakeMessage.meta_data.from_queue = "filter:before:spam";
		fakeMessage.meta_data.to_queue = "filter:after:spam";
		fakeMessage.mt_sms.udh = null;
		fakeMessage.mt_sms.charset = "UTF-8";
		fakeMessage.mt_sms.parts = 1;
		fakeMessage.mt_sms.coding = 0;
		fakeMessage.routing.cost = 0.3;
		// something randomized but not used
		fakeMessage.routing.route_id = randInt(1, 9);
		// here starts the useful stuff
		fakeMessage.account_id = 800 + randomSender;
		fakeMessage.meta_data.priority = randInt(0, 4);
		fakeMessage.mt_sms.from = "Sender"+senders[randomSender];
		int step = (randomSender + randInt(1, senders.length)) % senders.length;
		fakeMessage.mt_sms.to = senders[step];
		fakeMessage.mt_sms.text = "Message from "+senders[randomSender]+" to "+senders[step];
		return fakeMessage;
	}
	
	private static int randInt(int min, int max) {
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	}
	
}
