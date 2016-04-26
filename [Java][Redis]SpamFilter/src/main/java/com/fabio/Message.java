package com.fabio;

public class Message {
	String msgid, spid, insert_time, scheduled_send_time, type, hlr;
	int account_id;
	String pricing;

	MtSms mt_sms = new MtSms("", "", "", "", "", 0, 1);
	Routing routing = new Routing(0, 0);
	MetaData meta_data = new MetaData(0, "", "");
	Dlr dlr = new Dlr("","","","");

	public class Dlr {
		
		String dlr_time, stat, err, error;
		
		public Dlr(String dlr_time, String stat, String err, String error) {
			this.dlr_time = dlr_time;
			this.stat = stat;
			this.err = err;
			this.error = error;
		}		
	}

	public class MtSms {

		String from, to, text;
		String charset, udh;
		int coding, parts;

		public MtSms(String from, String to, String text, String charset, String udh, int coding, int parts) {
			this.from = from;
			this.to = to;
			this.text = text;
			this.charset = charset;
			this.udh = udh;
			this.coding = coding;
			this.parts = parts;
		}
	}

	public class Routing {
		double cost;
		int route_id;

		public Routing(double cost, int route_id) {
			this.cost = cost;
			this.route_id = route_id;
		}
	}

	public class MetaData {

		int priority;
		String from_queue, to_queue;

		public MetaData(int priority, String from_queue, String to_queue) {
			this.priority = priority;
			this.from_queue = from_queue;
			this.to_queue = to_queue;
		}
	}

}
