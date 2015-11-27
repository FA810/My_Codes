package coding.question.three.test;

import java.util.Arrays;

import coding.question.three.collections.Bag;
import junit.framework.TestCase;

public class TestBag extends TestCase {
	//data structure for String
	Bag<String> bsw = new Bag<String>();
	String myWords[] = {"home","web","folder","home","desktop","folder",
			"folder","home","home","ubuntu","mouse","keyboard","mouse",
			"printer","linux","ubuntu","system","cpu","driver","disk",
	"driver"};
	//data structure for Integer
	Bag<Integer> bsi = new Bag<Integer>();
	Integer myNumbers[] = {1,99,1,56,45,99,1,1,2,15,45,65,56,48,
			99,99,99,99,31,32,33,34,35,36,3,33,33,22};


	/* Check addAll for String and Integer */
	public void testAddAllString(){
		assertTrue(bsw.addAll(Arrays.asList(myWords)));		
	}

	public void testAddAllInteger(){
		assertTrue(bsi.addAll(Arrays.asList(myNumbers)));
	}

	/* Check size for String and Integer */
	public void testSizeString(){
		testAddAllString();
		assertEquals(21, bsw.size());
		assertTrue(bsw.add("dvd"));
		assertEquals(22, bsw.size());
	}

	public void testSizeInteger(){
		testAddAllInteger();
		assertEquals(28 , bsi.size());
		assertTrue(bsi.add(98));
		assertEquals(29, bsi.size());
	}

	/* Check remove for String and Integer */
	public void testRemoveString(){
		testAddAllString();
		assertTrue(bsw.remove("home"));
		assertEquals(17, bsw.size());
		assertTrue(bsw.remove("folder",2));
		assertEquals(15, bsw.size());
		assertTrue(bsw.remove("folder"));
		assertEquals(14, bsw.size());
	}

	public void testRemoveInteger(){
		testAddAllInteger();
		assertTrue(bsi.remove(1));
		assertEquals(24 , bsi.size());
		assertTrue(bsi.remove(99,2));
		assertEquals(22 , bsi.size());
		assertTrue(bsi.remove(99));
		assertEquals(18 , bsi.size());
		assertFalse(bsi.remove(1000));
	}

	/* Check retainAll for String and Integer */
	public void testRetainAllString(){
		testAddAllString();
		String[] myRemaining = {"home","driver"};
		assertTrue(bsw.retainAll(Arrays.asList(myRemaining)));
		assertEquals(6 , bsw.size());
	}

	public void testRetainAllInteger(){
		testAddAllInteger();
		Integer[] myRemaining = {1,99,56};
		assertTrue(bsi.retainAll(Arrays.asList(myRemaining)));
		assertEquals(12 , bsi.size());
	}

	/* Check isEmpty (and clear) for String and Integer */
	public void testIsEmptyString(){
		testAddAllString();
		assertFalse(bsw.isEmpty());
		bsw.clear();
		assertTrue(bsw.isEmpty());
	}

	public void testIsEmptyInteger(){
		testAddAllInteger();
		assertFalse(bsi.isEmpty());
		bsi.clear();
		assertTrue(bsi.isEmpty());
	}

	/* Check contains for String and Integer */
	public void testContainsString(){
		testAddAllString();
		assertTrue(bsw.contains("home"));
		assertFalse(bsw.contains("---mmm"));
	}

	public void testContainsInteger(){
		testAddAllInteger();
		assertTrue(bsi.contains(99));
		assertFalse(bsi.contains(999));
	}
}
