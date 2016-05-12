package measures;

import java.text.DecimalFormat;
import java.text.ParseException;

public class TrackMeasure extends GenericMeasure {

	Double a, b, c;
	private Double addToManualTiming;

	public TrackMeasure(Double a, Double b, Double c, Double addToManualTiming) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.addToManualTiming = addToManualTiming;
	}

	public Integer getEvaluation(Double timeInSeconds) {
		Double result = a * Math.pow((b - timeInSeconds), c);
		return Integer.valueOf((result).intValue());
	}
	
	public Integer getEvaluation(String timeInSeconds) {
		return getEvaluation(reduceStringInDouble(timeInSeconds));
	}
	
	/*
	 * This to detect manual timing and convert it with additional time.
	 * Manual timing has only one decimal digit and adds: 
	 * - 0.24 seconds under 400m (100m and 110m hurdle)
	 * - 0.14 seconds at 400m 
	 */
	protected Double reduceStringInDouble(String timeInSeconds){
		Double number = 0d;
		Double more = 0d;
		String[] splitter = timeInSeconds.split("\\.");
		if ((splitter.length > 1)) {
			if (splitter[1].length() > 2) {
				timeInSeconds = splitter[0] + "." + splitter[1].substring(0, 2);
			} else if (splitter[1].length() == 1) {
				more += addToManualTiming;
			};
		};
		DecimalFormat decimalFormat = new DecimalFormat("#.##");		
		try {
			number = decimalFormat.parse(timeInSeconds).doubleValue() +more;
		} catch (ParseException e1) {
			System.out.println("Parsing Exception: "+e1);
		}
		return number;		
	}
}
