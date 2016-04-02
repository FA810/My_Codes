package carte;

import java.util.ArrayList;
import java.util.List;

public class Squadra extends GiocatoreScopone{
	List<GiocatoreScopone> players;
	CartePrese cartePrese;
	String name;
	
	public CartePrese getCartePrese() {
		return this.cartePrese;
	}

	public void setCartePrese(CartePrese cartePrese) {
		this.cartePrese = cartePrese;
	}

	
	public String getName() {
		return name.substring(1);
	}

	public void setName(String name) {
		this.name = this.name + " " +name;
	}

	public Squadra() {
		this.players = new ArrayList<GiocatoreScopone>();
		this.cartePrese = new CartePrese();
		this.name = new String();
	}
	
	public void addGiocatore(GiocatoreScopone giocatore){
		players.add(giocatore);
		this.setName(giocatore.name);
		cartePrese.addCards(giocatore.getCartePrese().getCarte());
		cartePrese.getScope().addAll(giocatore.getCartePrese().getScope());
		
	}

	public void printCards(){
		System.out.println("\nSquadra: "+this.getName());
		//System.out.println("Scope:\t\t"+this.cartePrese.getScope().size());
		//cartePrese.printSemePerRiga();
		
	}
}
