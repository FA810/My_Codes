package carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import carte.Carta.Seme;

public class PartitaScopone {

	static Random rand = new Random();
	static Tavolo tavolo = new Tavolo();
	static List<ManoScopone> mani = new ArrayList<ManoScopone>();
	static List<GiocatoreScopone> giocatori = new ArrayList<GiocatoreScopone>();
	static int numGiocatori = 4;

	private static int randomNumber(int min, int max) {
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	}

	public void buttaSuTavolo(Carta carta) {
		tavolo.addCard(carta);
	}

	public void presaDiretta(Carta carta) {

	}

	public static void main(String[] args) {
		Mazzo mazzo = new Mazzo();
		//mazzo.mischia();
		mazzo.sortByValore();
		// mazzo.printCarte();
		System.out.println("mazzo da "+mazzo.getSize()+" mischiato ");
		
		
		//mazzo.removeCard(7, Seme.DENARI);
		//mazzo.removeCardAt(2);
		//mazzo.removeCardAt(0);
		//mazzo.removeCardAt(5);
		/*
		for (int i = 18; i < 26; i++) {
			mazzo.removeCardAt(i-5);
		}
		mazzo.addCard(new Carta(Seme.DENARI,4));
		//mazzo.addCard(new Carta(Seme.DENARI,5));
		mazzo.addCard(new Carta(Seme.DENARI,6));
		//mazzo.addCard(new Carta(Seme.DENARI,7));
		*/
		mazzo.printPrimieraPerRiga();		
		Valutatore v = new Valutatore(mazzo);
		//if (mazzo.getSize() <= 50) return;
		
		List<ManoScopone> mani = new ArrayList<ManoScopone>();
		
		/* creazione mani e giocatori */
		for (int i = 0; i < numGiocatori; i++) {
			ManoScopone mano = new ManoScopone();
			GiocatoreScopone giocatore = new GiocatoreScopone(mano);
			giocatore.setName("Player"+(i+1));
			giocatori.add(giocatore);
			mani.add(mano);
		}
		/* distribuzione carte ai giocatori */
		for (int i = 0; i < mazzo.numeroCarte; i++) {
			Carta c = mazzo.getCarte().get(i);
			giocatori.get(i % numGiocatori).getMano().addCard(c);
		}
		/* stampa carte di ogni giocatore ordinate */
		for (GiocatoreScopone temp : giocatori) {
			System.out.println("\n--------Mano Giocatore "+temp.getName());
			temp.getMano().printSemePerRiga();
			//temp.getMano().printValorePerRiga();
		}
		
		
		int turno;
		GiocatoreScopone giocatoreTemp;
		ManoScopone manoTemp;
		Carta cartaTemp;
		Carta cartaFound;
		for (int i = 0; i < mazzo.numeroCarte; i++) {
			turno = i % numGiocatori;
			giocatoreTemp = giocatori.get(turno);
			manoTemp = giocatoreTemp.getMano();
			cartaTemp = manoTemp.removeCardAt(0);//randomNumber(0, manoTemp.getSize()));
			System.out.println(giocatoreTemp.getName()+" butta "+cartaTemp);
			cartaFound = tavolo.isCartaContained(cartaTemp.getValore());
			if(cartaFound != null){
				giocatoreTemp.prendiCarta(cartaTemp);
				giocatoreTemp.prendiCarta(cartaFound);
				tavolo.removeCard(cartaFound);
			} else {
				tavolo.addCard(cartaTemp);
			}
			if (turno == 3) {
				System.out.println("Tavolo: ");
				tavolo.printSemePerRiga();
				System.out.println("\n--------Turno ");
				for (GiocatoreScopone temp : giocatori) {
					System.out.println("\n--------Mano Giocatore "+temp.getName());
					temp.getMano().printSemePerRiga();
				}				
				for (GiocatoreScopone temp : giocatori) {
					System.out.println("\n--------Prese Giocatore "+temp.getName());
					temp.getCartePrese().printValorePerRiga();
					//temp.getMano().printValorePerRiga();
				}
			}
		}
		//tavolo.printSemePerRiga();
		/* stampa carte di ogni giocatore ordinate */
		/*for (GiocatoreScopone temp : giocatori) {
			System.out.println("\n--------Prese Giocatore "+temp.getName());
			temp.getCartePrese().printSemePerRiga();
			//temp.getMano().printValorePerRiga();
		}*/

	}

}
