package measures;

public class ThrowMeasure extends GenericMeasure {

	Double a, b, c;

	public ThrowMeasure(Double a, Double b, Double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Integer getEvaluation(Double distanceInMeters) {		
		Double result = a * Math.pow((distanceInMeters - b), c);
		return Integer.valueOf((result).intValue());
	}
	
	public Integer getEvaluation(String distanceInMeters) {
		return getEvaluation(transformCentimetersInMeters(reduceStringInDouble(distanceInMeters)));
	}
	
	protected Double reduceStringInDouble(String jumpInCentimeters){
		return super.reduceStringInDouble(jumpInCentimeters);
	}
	
	private Double transformCentimetersInMeters(Double distance){
		if(distance>130){
			distance /= 100;
		}
		return distance;
	}

}
