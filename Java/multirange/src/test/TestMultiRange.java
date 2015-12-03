package test;

import ranges.MultiRange;
import ranges.Range;

public class TestMultiRange {
	
	public static void main(String args[]){
		MultiRange mr = new MultiRange();

		mr.addRange(new Range(10,18));mr.showValues();		
		mr.addRange(new Range(15,30));mr.showValues();
		mr.addRange(new Range(14,50));mr.showValues();
		mr.addRange(new Range(80,100));mr.showValues();
		mr.addRange(new Range(71,300));mr.showValues();
		mr.addRange(new Range(71,200));mr.showValues();
		mr.addRange(new Range(5,10));mr.showValues();
		mr.addRange(new Range(2000,4000));mr.showValues();
		mr.addRange(new Range(3000,4300));mr.showValues();

		mr.removeRange(new Range(3200,4000));mr.showValues();
		mr.removeRange(new Range(71,74));mr.showValues();
		mr.removeRange(new Range(91,124));mr.showValues();
		mr.removeRange(new Range(126,127));mr.showValues();
		mr.removeRange(new Range(30000,40000));mr.showValues();
		
	}

}
