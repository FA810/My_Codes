package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class HighJumpMeasureTest {
	
	JumpMeasure hjm = new HighJumpMeasure();
	
	@Test
	public void shouldReturnProperValuesFromMetersOrCentimeters() {
		assertEquals(1392, hjm.getEvaluation("259"));
		assertEquals(1392, hjm.getEvaluation("2.59"));
		assertEquals(1285, hjm.getEvaluation("249"));
		assertEquals(1285, hjm.getEvaluation("2.49 "));
		assertEquals(696, hjm.getEvaluation("188"));
		assertEquals(696, hjm.getEvaluation("1.88"));
		assertEquals(116, hjm.getEvaluation("107"));
		assertEquals(116, hjm.getEvaluation("1.07"));
		assertEquals(111, hjm.getEvaluation("106"));
		assertEquals(111, hjm.getEvaluation("1.06 "));
		assertEquals(81, hjm.getEvaluation("1.00"));
		assertEquals(86, hjm.getEvaluation("1.01"));
	}	
}
