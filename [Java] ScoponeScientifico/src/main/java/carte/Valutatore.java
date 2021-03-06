package carte;

import java.util.Collections;

public class Valutatore {
	
	private static int MIN_CARTE = 20;
	private static int MIN_DENARI = 5;
	
	private int setteBello;
	private int reBello;
	private int primiera;
	private int primieraWin;
	private int carte;
	private int denari;
	private int napoli;
	private int scope;
	GruppoCarte gruppoCarte;
	
	public Valutatore(GiocatoreScopone giocatore) {
		this(giocatore.getCartePrese());
	}
	
	public Valutatore(GruppoCarte gruppoCarte) {
		this.gruppoCarte = gruppoCarte;
		this.setteBello = 0;
		this.reBello = 0;
		this.denari = 0;
		this.napoli = 0;
		this.scope = gruppoCarte.getScope().size();
		this.carte = gruppoCarte.getSize();		
		this.findPrimieraVincente();
		this.valutaDenariSetteRe();
		this.printValutazione();
	}
	
	public int totalizza(){
		return this.setteBello + this.reBello + this.denari/6 + this.primieraWin + this.scope + this.carte/21 + this.napoli;
	}
	
	public int valutaCarta(int valore, Carta.Seme seme){
		for(Carta carta: gruppoCarte.getCarte()){
			if((carta.getValore() == valore) && (carta.getSeme() == seme)){
				return 1;
			}
		}
		return 0;
	}
	
	public void valutaCarte(){
		this.carte = gruppoCarte.getSize();
	}
	
	public boolean isSetteBello(Carta carta){
		return ((carta.getValore() == 7) && (carta.getSeme() == Carta.Seme.DENARI));
	}
	
	public boolean isReBello(Carta carta){
		return ((carta.getValore() == 10) && (carta.getSeme() == Carta.Seme.DENARI));
	}
	
	public void valutaDenariSetteRe(){
		boolean[] denariNapoli = new boolean[10];		
		for(Carta carta: gruppoCarte.getCarte()){
			if(carta.getSeme() == Carta.Seme.DENARI){
				this.denari += 1;
				if(isSetteBello(carta)){
					this.setteBello = 1;
				}
				if(isReBello(carta)){
					this.reBello = 1;
				}
				denariNapoli[carta.getValore()-1] = true;
			}
		}
		
		for(int i=0; i<denariNapoli.length; i++){
			//System.out.println(denariNapoli[i]);
			if(denariNapoli[i] == false){
				break;
			} else if(i>=2){
				this.napoli = i+1;
			}
			
		}
	}
	
	public void findPrimieraVincente(){
		this.primiera = gruppoCarte.findPrimiera();
		Mazzo tempMazzo = new Mazzo();
		tempMazzo.removeCards(gruppoCarte.getCarte());
		if(tempMazzo.findPrimiera() >= this.primiera)
			primieraWin = 0;
		else primieraWin = 1;
	}
	
	public void printValutazione(){
		System.out.println("*** Valori");
		System.out.println("Carte:\t\t"+this.carte/21+" ("+this.carte+")");
		System.out.println("Denari:\t\t"+this.denari/6+" ("+this.denari+")");
		System.out.println("Scope:\t\t"+this.scope);
		System.out.println("Primiera Win:\t"+this.primieraWin+" ("+this.primiera+")");		
		System.out.println("Settebello:\t"+this.setteBello);
		System.out.println("Rebello:\t"+this.reBello);
		System.out.println("Napoli:\t\t"+this.napoli);		
	}
	
	

}
