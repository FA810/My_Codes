package carte;

import java.util.Collections;
import java.util.Random;


public class Mazzo extends GruppoCarte{
	
	int numeroCarte = 40;
	int cartePerSeme = 10;

	static Random rand = new Random();
		
	public Mazzo(int numeroCarte) {
		this.numeroCarte = numeroCarte;
		for(int i=0; i<numeroCarte;i++){
			this.getCarte().add(new Carta(Carta.Seme.values()[(i/cartePerSeme)%Carta.Seme.values().length], (i%cartePerSeme)+1));
		}
	}
	
	public Mazzo() {		
		this(40);
	}

	public void mischia(){		
		int random;		
		for (int i = 0; i < this.numeroCarte; i++) {
			random = randInt(0, numeroCarte);
			if (i != random) {
				this.scambia(i, random);
			}
		}
	}
	
	public void scambia(int i, int j){
		Collections.swap(this.getCarte(), i, j);
	}
	
	private static int randInt(int min, int max) {
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	}

}
