package measures;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class M110HurdleMeasureTest {
	
	TrackMeasure m110HurdleM = new M110HurdleMeasure();	

	@Test
	public void shouldReturnManualTimeValues() {		
		assertEquals(m110HurdleM.getEvaluation("12.0"), m110HurdleM.getEvaluation("12.24"));
		assertEquals(m110HurdleM.getEvaluation(12.00), m110HurdleM.getEvaluation("12.00"));
		assertEquals(m110HurdleM.getEvaluation("12.3"), m110HurdleM.getEvaluation("12.54"));
		assertEquals(m110HurdleM.getEvaluation("12.3"), m110HurdleM.getEvaluation(12.54));
		assertEquals(1243, m110HurdleM.getEvaluation("12.04"));
		assertEquals(1158, m110HurdleM.getEvaluation("12.4"));
		assertFalse(m110HurdleM.getEvaluation(12.1) == m110HurdleM.getEvaluation("12.1"));
		assertFalse(m110HurdleM.getEvaluation(12.3) == m110HurdleM.getEvaluation("12.3"));
	}
	
	@Test
	public void shouldReturnAutoTimeValues() {
		assertEquals(m110HurdleM.getEvaluation(12.23), m110HurdleM.getEvaluation("12.23"));
		assertEquals(1216, m110HurdleM.getEvaluation("12.23"));
		assertEquals(1216, m110HurdleM.getEvaluation(12.23));
	}
	
	@Test
	public void shouldReturnProperTimeValuesInStringOrDouble() {
		assertEquals(1222, m110HurdleM.getEvaluation("12.19"));
		assertEquals(1222, m110HurdleM.getEvaluation(12.19));
		assertEquals(1220, m110HurdleM.getEvaluation("12.20"));
		assertEquals(1220, m110HurdleM.getEvaluation(12.20));
		assertEquals(886, m110HurdleM.getEvaluation(14.70));
		assertEquals(886, m110HurdleM.getEvaluation("14.70"));
		assertEquals(874, m110HurdleM.getEvaluation("14.80"));
		assertEquals(874, m110HurdleM.getEvaluation(14.80));
		assertEquals(870, m110HurdleM.getEvaluation("14.83"));
		assertEquals(870, m110HurdleM.getEvaluation(14.83));
		assertEquals(324, m110HurdleM.getEvaluation("20.33"));
		assertEquals(324, m110HurdleM.getEvaluation(20.33));
		assertEquals(1214, m110HurdleM.getEvaluation("12.0"));
		assertEquals(1075, m110HurdleM.getEvaluation("13.0"));
		assertEquals(944, m110HurdleM.getEvaluation("14.0"));
		assertEquals(129, m110HurdleM.getEvaluation("23.2"));
		assertEquals(18, m110HurdleM.getEvaluation("26.4"));
		assertEquals(455, m110HurdleM.getEvaluation("18.5"));
	}
	
}
