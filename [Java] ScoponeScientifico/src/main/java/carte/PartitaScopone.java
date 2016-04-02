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
	static GiocatoreScopone lastCapturingPlayer;
	static int numGiocatori = 4;
	static Squadra[] teams = new Squadra[2];

	private static int randomNumber(int min, int max) {
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	}

	private static void printManiGiocatori() {
		for (GiocatoreScopone temp : giocatori) {
			System.out.println("\n--------Mano Giocatore " + temp.getName());
			temp.getMano().printSemePerRiga();
		}
	}

	private static void printPreseGiocatori() {
		for (GiocatoreScopone temp : giocatori) {
			System.out.println(
					"\n--------Prese Giocatore " + temp.getName() + "  --- n: " + temp.getCartePrese().getSize());
			temp.getCartePrese().printValorePerRiga();
		}
	}

	private static void printScopeGiocatori() {
		System.out.println("\n--------Scope Giocatore ");
		for (GiocatoreScopone temp : giocatori) {
			System.out.println("Giocatore " + temp.getName() + " : " + temp.getScope());
		}
	}

	private static void printTavolo() {
		System.out.println("\n--------Tavolo ");
		tavolo.printSemePerRiga();
	}

	public static void main(String[] args) {
		Mazzo mazzo = new Mazzo();
		//mazzo.mischia();
		mazzo.sortByValore();
		// mazzo.printCarte();
		System.out.println("mazzo mischiato da " + mazzo.getSize());


		// mazzo.printValorePerRiga();//.printPrimieraPerRiga();
		Valutatore v= new Valutatore(mazzo);
		// if (mazzo.getSize() <= 50) return;

		List<ManoScopone> mani = new ArrayList<ManoScopone>();

		/* creazione mani e giocatori */
		for (int i = 0; i < numGiocatori; i++) {
			ManoScopone mano = new ManoScopone();
			GiocatoreScopone giocatore = new GiocatoreScopone(mano);
			giocatore.setName("Player" + (i + 1));
			giocatori.add(giocatore);
			mani.add(mano);
			
		}
		lastCapturingPlayer = giocatori.get(0);
		/* distribuzione carte ai giocatori */
		for (int i = 0; i < mazzo.numeroCarte; i++) {
			Carta c = mazzo.getCarte().get(i);
			giocatori.get(i % numGiocatori).getMano().addCard(c);
		}
		/* stampa carte di ogni giocatore ordinate */
		//printManiGiocatori();

		int turno;
		GiocatoreScopone giocatoreTemp;
		ManoScopone manoTemp;
		Carta cartaTemp;

		for (int i = 0; i < mazzo.numeroCarte; i++) {

			// stabilire di chi è il turno e guardare la sua mano
			turno = i % numGiocatori;
			giocatoreTemp = giocatori.get(turno);
			manoTemp = giocatoreTemp.getMano();
			// quale carta giocare?
			cartaTemp = manoTemp.removeCardAt(0);//manoTemp.removeCardAt(randomNumber(0,manoTemp.getSize()));//  
			System.out.println(giocatoreTemp.getName() + " butta " + cartaTemp);

			// controlla se effettua presa oppure aggiunge soltanto al tavolo
			if (giocatoreTemp.giocaCarta(tavolo, cartaTemp).size() > 0) {
				lastCapturingPlayer = giocatoreTemp;
				if ((tavolo.getCarte().size() == 0) ) {
					if ((turno != numGiocatori - 1) || (manoTemp.getSize() > 0)){
						if(((cartaTemp.getValore() == 1) && !(Settings.ASSO_PIGLIA_TUTTO))||(cartaTemp.getValore() != 1)){
							System.out.print(" --- scopa!  ");
							giocatoreTemp.setScope(cartaTemp);
						}											
					} 
					/*else if (manoTemp.getSize() > 0) {
						System.out.print(" --- scopa!  ");
						giocatoreTemp.setScope(cartaTemp);
					}*/
					//if(((cartaTemp.getValore() == 1) && !(Settings.ASSO_PIGLIA_TUTTO))||(cartaTemp.getValore() != 1)){}
				}
				System.out.println(" --- e prende  ");
			}
			;

			if (turno == 3) {
				printTavolo();
				System.out.println("\n--------Turno ");
				// printManiGiocatori();

			}
		}
		if (tavolo.getCarte().size() > 0) {
			// all'ultimo che prende, dai le carte rimaste sul tavolo
			List<Carta> restanti = tavolo.getCarte();
			lastCapturingPlayer.addCartePrese(restanti);
			tavolo = new Tavolo();
		}
		System.out.println("Last taking: " + lastCapturingPlayer.getName());
		// tavolo.printSemePerRiga();
		/* stampa carte di ogni giocatore ordinate */
		printPreseGiocatori();
		printScopeGiocatori();
		
		for(int y = 0; y<2; y++){
			teams[y] = new Squadra();
			teams[y].addGiocatore(giocatori.get(y));
			teams[y].addGiocatore(giocatori.get(y+2));
			teams[y].printCards();
			v = new Valutatore(teams[y].getCartePrese());
			System.out.println("totale: "+v.totalizza());
		}


	}

}
