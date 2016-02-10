package carte;

import java.util.ArrayList;
import java.util.List;

public class GiocatoreScopone {
	
	String name;
	ManoScopone mano;
	CartePrese cartePrese = new CartePrese();
	
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
	
	public void prendiCarte(List<Carta> carte){
		cartePrese.addCards(carte);
	}
	
	public void prendiCarta(Carta carta){
		cartePrese.addCard(carta);
	}
	
	public void MostraCarte(){
		mano.printSemePerRiga();
	}
	
	
}
