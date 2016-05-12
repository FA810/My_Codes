package measures;

public class JumpMeasure extends GenericMeasure {

	Double a, b, c;

	public JumpMeasure(Double a, Double b, Double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Integer getEvaluation(Double jumpInCentimeters) {		
		Double result = a * Math.pow((jumpInCentimeters - b), c);
		return Integer.valueOf((result).intValue());
	}
	
	public Integer getEvaluation(String jumpInCentimeters) {
		return getEvaluation(transformMetersInCentimeters(reduceStringInDouble(jumpInCentimeters)));
	}
	
	protected Double reduceStringInDouble(String jumpInCentimeters){
		return super.reduceStringInDouble(jumpInCentimeters);
	}
	
	private Double transformMetersInCentimeters(Double jumpHeight){
		if(jumpHeight<40){
			jumpHeight *= 100;
		}
		return jumpHeight;
	}

}
