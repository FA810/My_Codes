package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShotPutMeasureTest {
	
	ThrowMeasure spm = new ShotPutMeasure();
	
	@Test
	public void shouldReturnProperValuesFromMetersOrCentimeters() {
		assertEquals(1350, spm.getEvaluation("23.99"));
		assertEquals(1350, spm.getEvaluation("2399"));
		assertEquals(1340, spm.getEvaluation("23.83"));
		assertEquals(1340, spm.getEvaluation("2383"));
		assertEquals(1339, spm.getEvaluation("23.82"));
		assertEquals(1339, spm.getEvaluation("2382"));
		assertEquals(1153, spm.getEvaluation("20.85"));
		assertEquals(1153, spm.getEvaluation("2085"));
		assertEquals(502, spm.getEvaluation("10.27"));
		assertEquals(502, spm.getEvaluation("1027 "));
		assertEquals(18, spm.getEvaluation("1.87"));
		assertEquals(17, spm.getEvaluation("186"));
	}	
}
