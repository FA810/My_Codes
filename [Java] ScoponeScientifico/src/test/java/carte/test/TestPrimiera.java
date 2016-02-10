package carte.test;

import org.junit.Test;
import org.junit.Assert;

import carte.Primiera;


public class TestPrimiera {
	
	@Test
	public void shouldReturnCorrectValueForPrimiera() {
		Assert.assertEquals(Primiera.getPrimieraValue(1), 16);
		Assert.assertEquals(Primiera.getPrimieraValue(2), 12);
		Assert.assertEquals(Primiera.getPrimieraValue(3), 13);
		Assert.assertEquals(Primiera.getPrimieraValue(4), 14);
		Assert.assertEquals(Primiera.getPrimieraValue(5), 15);
		Assert.assertEquals(Primiera.getPrimieraValue(6), 18);
		Assert.assertEquals(Primiera.getPrimieraValue(7), 21);
		Assert.assertEquals(Primiera.getPrimieraValue(8), 10);
		Assert.assertEquals(Primiera.getPrimieraValue(9), 10);
		Assert.assertEquals(Primiera.getPrimieraValue(10), 10);
		Assert.assertEquals(Primiera.getPrimieraValue(11), 0);
		Assert.assertEquals(Primiera.getPrimieraValue(0), 0);
	}

}
