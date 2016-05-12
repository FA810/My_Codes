package measures;

import java.text.DecimalFormat;
import java.text.ParseException;

public class M1500Measure extends TrackMeasure {

	public M1500Measure() {
		super(0.03768, 480.00, 1.85, 0.0);
	}
	
	public Integer getEvaluation(String timeInSeconds) {
		return super.getEvaluation(reduceStringInDouble(timeInSeconds));
	}

	// this is different from the others because it might contain ":".
	public Double reduceStringInDouble(String timeInSeconds) {
		Double secondsFromMinutes = 0d;
		String[] splitter = timeInSeconds.split(":");
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		for(int i=0;i<splitter.length;i++){
			try {
				secondsFromMinutes += decimalFormat.parse(splitter[i]).doubleValue()*Math.pow(60, (splitter.length-1)-i);
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}
		return secondsFromMinutes;
	}
}
