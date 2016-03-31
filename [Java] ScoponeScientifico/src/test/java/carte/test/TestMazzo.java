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
	public void shouldBeInitializedEmptyAndThenAdded() {
		Mazzo mazzo = new Mazzo(0);
		Assert.assertEquals(0, mazzo.getSize());
		mazzo.addCard(new Carta(Seme.DENARI,7));
		Assert.assertEquals(1, mazzo.getSize());
		mazzo.addCard(Seme.DENARI,8);
		Assert.assertEquals(2, mazzo.getSize());
	}
	
	@Test
	public void shouldAddSimilarCardsAsDifferent() {
		Mazzo mazzo = new Mazzo(0);
		Assert.assertEquals(0, mazzo.getSize());
		mazzo.addCard(new Carta(Seme.DENARI,7));
		Assert.assertEquals(1, mazzo.getSize());
		mazzo.addCard(Seme.DENARI,7);
		Assert.assertEquals(2, mazzo.getSize());
	}
	
	@Test
	public void shouldProperlyRemoveCards() {
		Mazzo mazzo = new Mazzo(40);
		mazzo.removeCard(new Carta(Seme.COPPE,6));
		Assert.assertEquals(39, mazzo.getSize());
		mazzo.removeCard(new Carta(Seme.COPPE,7));
		Assert.assertEquals(38, mazzo.getSize());
	}
	
	@Test
	public void shouldProperlyMaintainSize() {
		Mazzo mazzo = new Mazzo(40);
		mazzo.removeCard(new Carta(Seme.COPPE,6));
		Assert.assertEquals(39, mazzo.getSize());
		mazzo.removeCard(new Carta(Seme.COPPE,6));
		Assert.assertEquals(39, mazzo.getSize());
	}
	
	@Test
	public void shouldAddListOfCards() {
		Mazzo mazzo = new Mazzo(40);
		Mazzo second = new Mazzo(10);
		mazzo.addCards(second.getCarte());
		Assert.assertEquals(50, mazzo.getSize());
	}
	
	@Test
	public void shouldBeProperlyFilled() {
		Mazzo mazzo = new Mazzo(10);
		for(int i = 0; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			Assert.assertEquals(i+1, carta.getValore());
			Assert.assertEquals(Seme.MAZZE, carta.getSeme());
		}
		mazzo = new Mazzo(20);
		int offset = 10 * Seme.COPPE.getCode();
		for(int i = offset; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			Assert.assertEquals(i+1-offset, carta.getValore());
			Assert.assertEquals(Seme.COPPE, carta.getSeme());
		}
		mazzo = new Mazzo(30);
		offset = 10 * Seme.SPADE.getCode();
		for(int i = offset; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			Assert.assertEquals(i+1-offset, carta.getValore());
			Assert.assertEquals(Seme.SPADE, carta.getSeme());
		}
		mazzo = new Mazzo(40);
		offset = 10 * Seme.DENARI.getCode();
		for(int i = offset; i<mazzo.getSize();i++){
			Carta carta = mazzo.getCarte().get(i);
			Assert.assertEquals(i+1-offset, carta.getValore());
			Assert.assertEquals(Seme.DENARI, carta.getSeme());
		}		
		
	}

}
