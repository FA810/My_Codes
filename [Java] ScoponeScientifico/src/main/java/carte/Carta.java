package carte;

import java.util.Comparator;

public class Carta {

	private Seme seme;
	private int valore;

	public Carta(Seme seme, int valore) {
		this.seme = seme;
		this.valore = valore;
	}	

	public Seme getSeme() {
		return seme;
	}

	public int getValore() {
		return valore;
	}
	
	public String toString() {
		return this.getSeme()+" "+this.getValore();
	}
	
	public int getPrimieraValue() {
		return Primiera.getPrimieraValue(this.getValore());
	}
	
	public enum Seme {
		MAZZE(0), COPPE(1), SPADE(2), DENARI(3);

		private final int seme;

		Seme(int seme) {
			this.seme = seme;
		}

		public int getCode() {
			return seme;
		}
	}

}
