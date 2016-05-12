package measures;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class M400MeasureTest {
	
	M400Measure m400m = new M400Measure();	

	@Test
	public void shouldReturnManualTimeValues() {		
		assertEquals(m400m.getEvaluation("41.54"), m400m.getEvaluation("41.4"));
		assertEquals(m400m.getEvaluation(41.54), m400m.getEvaluation("41.4"));
		assertEquals(1246, m400m.getEvaluation("41.4"));
		assertFalse(m400m.getEvaluation(41.4) == m400m.getEvaluation("41.4"));
	}
	
	@Test
	public void shouldReturnAutoTimeValues() {
		assertEquals(m400m.getEvaluation(41.47), m400m.getEvaluation("41.47"));
		assertEquals(1250, m400m.getEvaluation("41.47"));
		assertEquals(1250, m400m.getEvaluation("41.47"));
	}
	
	@Test
	public void shouldReturnProperTimeValuesInStringOrDouble() {
		assertEquals(1249, m400m.getEvaluation("41.49"));
		assertEquals(1249, m400m.getEvaluation(41.49));
		assertEquals(1246, m400m.getEvaluation("41.4"));
		assertEquals(1246, m400m.getEvaluation("41.54"));
		assertEquals(1244, m400m.getEvaluation(41.58));
		assertEquals(1244, m400m.getEvaluation("41.58"));
		assertEquals(5, m400m.getEvaluation("80.08"));
		assertEquals(5, m400m.getEvaluation(80.08));
		assertEquals(70, m400m.getEvaluation("73.75"));
		assertEquals(70, m400m.getEvaluation(73.75));
		assertEquals(68, m400m.getEvaluation("73.7"));
		assertEquals(70, m400m.getEvaluation(73.70));
		assertEquals(71, m400m.getEvaluation(73.69));
		assertEquals(1268, m400m.getEvaluation("41.0"));
		assertEquals(1268, m400m.getEvaluation(41.14));
		assertEquals(378, m400m.getEvaluation("60.9"));
	}
	
}
