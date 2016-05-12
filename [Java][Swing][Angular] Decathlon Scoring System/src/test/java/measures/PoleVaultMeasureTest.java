package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class PoleVaultMeasureTest {
	
	JumpMeasure pvm = new PoleVaultMeasure();
	
	@Test
	public void shouldReturnProperValuesFromMetersOrCentimeters() {
		assertEquals(1396, pvm.getEvaluation("649"));
		assertEquals(1396, pvm.getEvaluation("6.49"));
		assertEquals(1318, pvm.getEvaluation("626"));
		assertEquals(1318, pvm.getEvaluation("6.26 "));
		assertEquals(255, pvm.getEvaluation("256"));
		assertEquals(255, pvm.getEvaluation("2.56"));
		assertEquals(253, pvm.getEvaluation("255"));
		assertEquals(253, pvm.getEvaluation("2.55"));
		assertEquals(17, pvm.getEvaluation("121"));
		assertEquals(17, pvm.getEvaluation("1.21 "));
		assertEquals(5, pvm.getEvaluation("1.09"));
		assertEquals(6, pvm.getEvaluation("1.10"));
	}	
}
