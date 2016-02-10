package carte.test;


import org.junit.Assert;
import org.junit.Test;

import carte.Carta;
import carte.Carta.Seme;
import carte.Mazzo;

public class TestMazzo {

	@Test
	public void shouldBeProperlyInitialized() {
		Mazzo mazzo = new Mazzo(40);
		Assert.assertEquals(40, mazzo.getSize());
		mazzo = new Mazzo(40);
		Assert.assertEquals(40, mazzo.getSize());
	}
	
	@Test
	public void shouldBeProperlyFilled() {
		Mazzo mazzo = new Mazzo(10);
		for(int i = 0; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			//System.out.println(mazzo.getCarte().get(i));
			Assert.assertEquals(i+1, carta.getValore());
			Assert.assertEquals(Seme.MAZZE, carta.getSeme());
		}
		mazzo = new Mazzo(20);
		int offset = 10 * Seme.COPPE.getCode();
		for(int i = offset; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			//System.out.println(mazzo.getCarte().get(i));
			Assert.assertEquals(i+1-offset, carta.getValore());
			Assert.assertEquals(Seme.COPPE, carta.getSeme());
		}
		mazzo = new Mazzo(30);
		offset = 10 * Seme.SPADE.getCode();
		for(int i = offset; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			//System.out.println(mazzo.getCarte().get(i));
			Assert.assertEquals(i+1-offset, carta.getValore());
			Assert.assertEquals(Seme.SPADE, carta.getSeme());
		}
		mazzo = new Mazzo(40);
		offset = 10 * Seme.DENARI.getCode();
		for(int i = offset; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			//System.out.println(mazzo.getCarte().get(i));
			Assert.assertEquals(i+1-offset, carta.getValore());
			Assert.assertEquals(Seme.DENARI, carta.getSeme());
		}
		
		
	}

}
