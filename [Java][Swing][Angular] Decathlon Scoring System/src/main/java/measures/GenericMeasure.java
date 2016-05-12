package measures;

import java.text.DecimalFormat;
import java.text.ParseException;

public abstract class GenericMeasure {
	double a,b,c;
	
	public Integer getEvaluation(){
		return 0;
	}
	
	protected Double reduceStringInDouble(String jumpInCentimeters){
		Double number = 0d;
		DecimalFormat decimalFormat = new DecimalFormat("#.##");		
		try {
			number = decimalFormat.parse(jumpInCentimeters).doubleValue();
		} catch (ParseException e1) {
			System.out.println("Parsing Exception: "+e1);
		}
		return number;		
	}
}
