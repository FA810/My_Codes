package carte;

import java.util.ArrayList;
import java.util.List;

public class GiocatoreScopone {

	String name;
	ManoScopone mano;
	CartePrese cartePrese = new CartePrese();
	CardsCalculator calculator;
	List<Carta> scope = new ArrayList<Carta>();

	public List<Carta> getScope() {
		return scope;
	}

	public void setScope(Carta scopa) {
		this.scope.add(scopa);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CartePrese getCartePrese() {
		return cartePrese;
	}

	public void setCartePrese(CartePrese cartePrese) {
		this.cartePrese = cartePrese;
	}
	
	public void addCartePrese(List<Carta> carte) {
		this.cartePrese.addCards(carte);
	}

	public ManoScopone getMano() {
		return mano;
	}

	public void setMano(ManoScopone mano) {
		this.mano = mano;
	}

	public GiocatoreScopone(ManoScopone miaMano) {
		this.mano = miaMano;
		this.cartePrese = new CartePrese();
	}

	public GiocatoreScopone() {
		mano = new ManoScopone();
		this.cartePrese = new CartePrese();
	}

	public void prendiCarte(List<Carta> carte) {
		cartePrese.addCards(carte);
	}

	public void prendiCarta(Carta carta) {
		cartePrese.addCard(carta);
	}

	public void MostraCarte() {
		mano.printSemePerRiga();
	}

	public List<Carta> giocaCarta(Tavolo tavolo, Carta miaCarta) {
		List<Carta> cartaFound;
		List<Carta> result = new ArrayList<Carta>();
		calculator = new CardsCalculator(tavolo);
		// controlla se effettua presa oppure aggiunge soltanto al tavolo
		cartaFound = calculator.checkSameCards(miaCarta,tavolo);
		if (cartaFound.size() > 0) {
			prendiCarta(miaCarta);
			// se ci sono più carte uguali, prende la prima! CAMBIARE!
			prendiCarta(cartaFound.get(0));			
			tavolo.removeCard(cartaFound.get(0));
			result.add(cartaFound.get(0));
		} else {
			List<ArrayList<Carta>> opzioni = calculator.findAdditionCards(miaCarta);	
			// se ci sono più carte da prendere, prende le prime che trova! CAMBIARE!
			if(opzioni.size() > 0){
				prendiCarta(miaCarta);
				prendiCarte(opzioni.get(0));
				tavolo.removeCards(opzioni.get(0));
				result.addAll(opzioni.get(0));
			} else {
				// non sto prendendo nulla
				tavolo.addCard(miaCarta);
				
			}			
		}
		return result;
	}

}
