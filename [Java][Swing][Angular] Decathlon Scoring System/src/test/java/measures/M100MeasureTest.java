package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class M100MeasureTest {
	
	TrackMeasure m100m = new M100Measure();

	@Test
	public void shouldReturnManualTimeValues() {
		assertEquals(m100m.getEvaluation("10.64"), m100m.getEvaluation("10.4"));
		assertEquals(m100m.getEvaluation(10.64), m100m.getEvaluation("10.4"));
		assertEquals(m100m.getEvaluation("10.84"), m100m.getEvaluation("10.6"));
		assertEquals(m100m.getEvaluation(10.84), m100m.getEvaluation("10.6"));
		assertEquals(942, m100m.getEvaluation("10.4"));
	}
	
	@Test
	public void shouldReturnAutoTimeValues() {
		assertEquals(m100m.getEvaluation("10.64"), m100m.getEvaluation(10.64));
		assertEquals(999, m100m.getEvaluation("10.40"));
		assertEquals(999, m100m.getEvaluation(10.40));
	}
	
	@Test
	public void shouldReturnProperTimeValuesInStringOrDouble() {
		assertEquals(1096, m100m.getEvaluation("10.00"));
		assertEquals(1096, m100m.getEvaluation(10.00));
		assertEquals(1071, m100m.getEvaluation("10.10"));
		assertEquals(1071, m100m.getEvaluation(10.10));
		assertEquals(1013, m100m.getEvaluation("10.1"));
		assertEquals(1013, m100m.getEvaluation("10.34"));
		assertEquals(1013, m100m.getEvaluation(10.34));
		assertEquals(1047, m100m.getEvaluation("10.20"));
		assertEquals(1047, m100m.getEvaluation(10.20));
		assertEquals(1047, m100m.getEvaluation("010.20"));
		assertEquals(1047, m100m.getEvaluation(010.20));
	}
	
}
