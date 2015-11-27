package coding.question.two.test;

import coding.question.three.collections.Bag;
import coding.question.two.filecounter.TextAnalyzer;
import junit.framework.TestCase;

public class TestTextAnalyzer extends TestCase {
	
	static final String FILE_UTF8 = "textfile.txt";

	public void testReadUTF8() throws Exception {
		Bag<String> sample = TextAnalyzer.readUTF8(FILE_UTF8);
		//check if it works, file found
		assertNotNull(sample); 
		// check existent word
		assertTrue(sample.contains("small"));
		assertTrue(sample.remove("small"));
		assertFalse(sample.contains("small"));
		// check word-delimiters
		assertTrue(sample.contains("self‒esteem"));
		assertTrue(sample.contains("self~image"));
		// check non-existent word
		assertFalse(sample.contains("bbbbbbbbbb"));		
	}

}
