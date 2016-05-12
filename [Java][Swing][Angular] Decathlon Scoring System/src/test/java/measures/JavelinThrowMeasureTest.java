package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavelinThrowMeasureTest {
	
	ThrowMeasure jtm = new JavelinThrowMeasure();
	
	@Test
	public void shouldReturnProperValuesFromMetersOrCentimeters() {
		assertEquals(1400, jtm.getEvaluation("102.85"));
		assertEquals(1400, jtm.getEvaluation("10285"));
		assertEquals(1386, jtm.getEvaluation("102"));
		assertEquals(1386, jtm.getEvaluation("102.00"));
		assertEquals(1107, jtm.getEvaluation("84.12"));
		assertEquals(1107, jtm.getEvaluation("8412"));
		assertEquals(620, jtm.getEvaluation("52.09"));
		assertEquals(619, jtm.getEvaluation("5208"));
		assertEquals(31, jtm.getEvaluation("9.82"));
		assertEquals(32, jtm.getEvaluation("990"));
		assertEquals(1, jtm.getEvaluation("7.12"));
		assertEquals(2, jtm.getEvaluation("723"));
	}	
}
