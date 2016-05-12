package measures;

import static org.junit.Assert.*;

import org.junit.Test;

public class M1500MeasureTest {
	
	TrackMeasure m1500m = new M1500Measure();
	
	@Test
	public void shouldReturnProperTimeValuesFromStrings() {
		assertEquals(1250, m1500m.getEvaluation("3:22.23 "));
		assertEquals(1241, m1500m.getEvaluation("3:23.31 "));
		assertEquals(1231, m1500m.getEvaluation("3:24.52 "));
		assertEquals(1157, m1500m.getEvaluation("3:33.60 "));
		assertEquals(1170, m1500m.getEvaluation("3:31.99 "));
		assertEquals(1106, m1500m.getEvaluation("3:40.01 "));
		assertEquals(152, m1500m.getEvaluation("6:31.07"));
		assertEquals(3, m1500m.getEvaluation("7:49.34"));
		assertEquals(2, m1500m.getEvaluation("7:49.35"));
		assertEquals(1, m1500m.getEvaluation("7:52.34"));
		assertEquals(1, m1500m.getEvaluation("7:54.11 "));
		assertEquals(0, m1500m.getEvaluation("8:54.08 "));

	}
	
}
