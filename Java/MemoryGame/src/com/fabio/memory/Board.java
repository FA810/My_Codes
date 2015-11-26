package com.fabio.memory;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private Disposition disposition;
	private Square[] squares;
	private boolean[] found;	
	int foundSquares;
	private int boardSize;
	List<String> matchMoves; 
	//private char[] values;	

	int lineSize = 8;
	int columnSize;
	
	public boolean getFound(int i) {		
		return found[i];
	}

	public void setFound(int i) {
		if(found[i] == false){
			this.foundSquares++;
			this.found[i] = true;
		}
	}
	
	public int getFoundSquares() {
		return foundSquares;
	}
	
	public int getLineSize() {
		return lineSize;
	}
	
	public char getValueAt(int i) {
		return squares[i].getValue();
	}
	
	public int getBoardTotalSquares() {
		return boardSize;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}
	
	public boolean setMove(int first,int second) {
		matchMoves.add((first/lineSize)+","+(first%lineSize)+"-"+(second/lineSize)+","+(second%lineSize)+": "+squares[first].getValue()+" and "+squares[second].getValue());
		if(squares[first].getValue() == squares[second].getValue()){
			setFound(first);
			setFound(second);
			return true;
		}		
		return false;
	}
	
	public boolean isBoardComplete() {
		if(this.getFoundSquares() == this.getBoardTotalSquares()){
			return true;
		}		
		return false;
	}
	
	public Board(){
		this(new Disposition());		
	}

	public Board(Disposition disposition) {
		this.disposition = disposition;
		matchMoves = new ArrayList<String>();
		//this.values = disposition.getCards();
		boardSize = disposition.getCards().length;
		this.squares = new Square[boardSize];
		this.found = new boolean[boardSize];
		this.foundSquares=0;
		for(int i =0; i<boardSize; i++){
			squares[i] = new Square(disposition.getCards()[i]);
			found[i] = false;
		}
	}
	
	public void printMatchMoves() {
		System.out.println("Printing match moves:");
		if(matchMoves.isEmpty()){System.out.println("No moves.");}
		else for (String move : matchMoves) {
			System.out.println(move);
		}
		System.out.println("Found "+(foundSquares/2)+" couple"+(foundSquares/2 == 1 ? "" : "s")+" in "+matchMoves.size()+" move"+(matchMoves.size() == 1 ? "" : "s"));
	}
	
	public void showValues(){
		String line = "\n-------------------";
		
		System.out.println("Printing board: "+line);
		System.out.println("  0 1 2 3 4 5 6 7");
		for(int i =0; i<boardSize; i++){				
			if((i % lineSize) == 0){
				if(i>0){System.out.println();}
				System.out.print(i/lineSize);
			}
			if(found[i] == true){
				System.out.print(" "+squares[i].getValue());
			} else {
				System.out.print(" -");
			}			
		}
		System.out.println(line);
	}
	
	public void revealValues(){
		String line = "\n-------------------";
		
		System.out.println("Printing board: "+line);
		System.out.println("  0 1 2 3 4 5 6 7");
		for(int i =0; i<boardSize; i++){				
			if((i % lineSize) == 0){
				if(i>0){System.out.println();}
				System.out.print(i/lineSize);
			}
			System.out.print(" "+squares[i].getValue());	
		}
		System.out.println(line);
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public void setDisposition(Disposition disposition) {
		this.disposition = disposition;
	}

	public static void main(String[] args) {
		Disposition d = new Disposition();
		//d.printCardsDisposed();
		Board board = new Board(d);
		board.revealValues();
	}

}
