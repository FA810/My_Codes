package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongJumpMeasureTest {
	
	JumpMeasure ljm = new LongJumpMeasure();
	
	@Test
	public void shouldReturnProperValuesFromMetersOrCentimeters() {
		assertEquals(811, ljm.getEvaluation("699"));
		assertEquals(811, ljm.getEvaluation("6.99"));
		assertEquals(790, ljm.getEvaluation("690"));
		assertEquals(790, ljm.getEvaluation("6.90 "));
		assertEquals(739, ljm.getEvaluation("6.68 "));
		assertEquals(739, ljm.getEvaluation("668 "));
		assertEquals(736, ljm.getEvaluation("6.67"));
		assertEquals(736, ljm.getEvaluation("667"));
		assertEquals(206, ljm.getEvaluation("400"));
		assertEquals(206, ljm.getEvaluation("4.00"));
		assertEquals(14, ljm.getEvaluation("247 "));
		assertEquals(15, ljm.getEvaluation("2.48 "));

	}
	
}
