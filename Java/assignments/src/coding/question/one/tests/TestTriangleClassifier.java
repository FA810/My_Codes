package coding.question.one.tests;

import coding.question.one.classifiers.TriangleClassifier;
import junit.framework.TestCase;

/**
 * @author Fabio Rizzello
 *
 */
public class TestTriangleClassifier extends TestCase {

	/**
	 * Test method for 
	 * {@link coding.question.one.classifiers.TriangleClassifier#TriangleClassifier(int, int, int)}.
	 */
	public void testTriangleClassifier() {
		assertNotNull(new TriangleClassifier(66,66,80));
	}

	/**
	 * Test method for 
	 * {@link coding.question.one.classifiers.TriangleClassifier#classifyTriangle()}.
	 */
	public void testClassifyTriangle() {
		assertEquals(4,new TriangleClassifier(100,16,80).classifyTriangle());
		assertEquals(4,new TriangleClassifier(0,0,80).classifyTriangle());
		assertEquals(4,new TriangleClassifier(20,-20,80).classifyTriangle());
		assertEquals(1,new TriangleClassifier(66,60,49).classifyTriangle());
		assertEquals(2,new TriangleClassifier(66,66,80).classifyTriangle());
		assertEquals(3,new TriangleClassifier(66,66,66).classifyTriangle());
	}

}
