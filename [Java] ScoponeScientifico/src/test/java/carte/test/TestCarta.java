package carte.test;

import org.junit.Ignore;
import org.junit.Test;

import carte.Carta;
import carte.Carta.Seme;
import carte.Primiera;

import org.junit.Assert;

public class TestCarta {
	
	int valore = 4; 

	@Test
	public void shouldReturnCorrectValueForCoppe() {
		for(int valore=1; valore == 10; valore++){
			Carta carta = new Carta(Seme.COPPE,valore);
			Assert.assertNotNull(carta);
			Assert.assertEquals(carta.getSeme(), Seme.COPPE);
			Assert.assertEquals(carta.getValore(), valore);
			Assert.assertEquals(carta.toString(), "COPPE "+valore);
			Assert.assertEquals(carta.getPrimieraValue(),Primiera.getPrimieraValue(valore));
		}		
	}
	
	@Test
	public void shouldReturnCorrectValueForDenari() {
		for(int valore=1; valore == 10; valore++){
			Carta carta = new Carta(Seme.DENARI,valore);
			Assert.assertNotNull(carta);
			Assert.assertEquals(carta.getSeme(), Seme.DENARI);
			Assert.assertEquals(carta.getValore(), valore);
			Assert.assertEquals(carta.toString(), "DENARI "+valore);
			Assert.assertEquals(carta.getPrimieraValue(),Primiera.getPrimieraValue(valore));
		}
	}
	
	@Test
	public void shouldReturnCorrectValueForSpade() {
		for(int valore=1; valore == 10; valore++){
			Carta carta = new Carta(Seme.SPADE,valore);
			Assert.assertNotNull(carta);
			Assert.assertEquals(carta.getSeme(), Seme.SPADE);
			Assert.assertEquals(carta.getValore(), valore);
			Assert.assertEquals(carta.toString(), "SPADE "+valore);
			Assert.assertEquals(carta.getPrimieraValue(),Primiera.getPrimieraValue(valore));
		}
	}
	
	@Test
	public void shouldReturnCorrectValueForMazze() {
		for(int valore=1; valore == 10; valore++){
			Carta carta = new Carta(Seme.MAZZE,valore);
			Assert.assertNotNull(carta);
			Assert.assertEquals(carta.getSeme(), Seme.MAZZE);
			Assert.assertEquals(carta.getValore(), valore);
			Assert.assertEquals(carta.toString(), "MAZZE "+valore);
			Assert.assertEquals(carta.getPrimieraValue(),Primiera.getPrimieraValue(valore));
		}		
	}

}