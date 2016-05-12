package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiscusThrowMeasureTest {
	
	ThrowMeasure dtm = new DiscusThrowMeasure();
	
	@Test
	public void shouldReturnProperValuesFromMetersOrCentimeters() {
		assertEquals(1500, dtm.getEvaluation("79.41"));
		assertEquals(1500, dtm.getEvaluation("7941"));
		assertEquals(1470, dtm.getEvaluation("78.04"));
		assertEquals(1470, dtm.getEvaluation("7804"));
		assertEquals(980, dtm.getEvaluation("55.22"));
		assertEquals(980, dtm.getEvaluation("5522"));
		assertEquals(660, dtm.getEvaluation("39.76"));
		assertEquals(660, dtm.getEvaluation("3976"));
		assertEquals(210, dtm.getEvaluation("16.63"));
		assertEquals(209, dtm.getEvaluation("1662"));
		assertEquals(208, dtm.getEvaluation("16.53"));
		assertEquals(207, dtm.getEvaluation("1651"));
	}	
}
