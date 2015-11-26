package com.fabio.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoryMatch {

	List<String> matchMoves = new ArrayList<String>();
	Disposition disposition;
	Board myBoard;

	public MemoryMatch(Board myBoard) {
		this.myBoard = myBoard;
		if(myBoard.getDisposition() == null){
			myBoard.setDisposition(new Disposition());
		}
		this.disposition = myBoard.getDisposition();
	}
	
	public MemoryMatch() {
		this(new Board());		
		this.disposition = myBoard.getDisposition();

	}
	
	public void startANewMatch(Disposition disposition) {
		this.matchMoves.clear();
		if(disposition == null){
			this.disposition = new Disposition();
		} else {
			this.disposition = disposition;
		}		
		//this.disposition.shuffleDisposition();
		this.myBoard = new Board(disposition);
		if (Settings.showCompleteBoardOnStartup) {
			myBoard.revealValues();
		};
		playWithKeyboardInput(myBoard);
	}

	public static void main(String[] args) {
		MemoryMatch memoryMatch = new MemoryMatch();
		memoryMatch.startANewMatch(new Disposition());
		
	}

	public void playWithKeyboardInput(Board myBoard) {		
		
		String userInput = new String("something");
		Scanner scanner = new Scanner(System.in);
		String[] points;
		int value1, value2;
		System.out.println("format: c1row,c1column-c2row,c2column   example: 2,3-4,5");
		boolean inputIsCorrect;
		while (areThereSquaresLeft() && !doesUserWantToQuit(userInput) && !doesUserWantToRestart(userInput)) {

			do {
				System.out.println("\n> Choose a card (or type \""+Settings.buttonForOptions+"\" to show options list): ");
				userInput = scanner.nextLine();
				if (doesUserWantToQuit(userInput) || doesUserWantToRestart(userInput)) {
					break;
				};
				inputIsCorrect = checkKeyboardInput(userInput);
			} while (!inputIsCorrect);
		}
		myBoard.showValues();
		myBoard.printMatchMoves();		
		System.out.println("Game over");
		if(doesUserWantToRestart(userInput)){System.out.println("Start a new game"); startANewMatch(new Disposition());}
	}
	
	public boolean areThereSquaresLeft(){
		return (!myBoard.isBoardComplete());
	}
	
	public boolean doesUserWantToQuit(String userInput){
		return (userInput.equalsIgnoreCase(Settings.buttonForExit));
	}
	
	public boolean doesUserWantToRestart(String userInput){
		return userInput.equalsIgnoreCase(Settings.buttonForRestart);
	}
	
	public boolean checkOptionsInput(String userInput){
		if (userInput.equalsIgnoreCase(Settings.buttonForBoard)) {
			myBoard.showValues();
			return true;
		};
		if (userInput.equalsIgnoreCase(Settings.buttonForMoveList)) {
			myBoard.printMatchMoves();
			return true;
		};
		if (userInput.equalsIgnoreCase(Settings.buttonForOptions)) {
			printOptions();
			return true;
		};
		if (userInput.equalsIgnoreCase(Settings.buttonForSolution)) {
			myBoard.revealValues();
			return true;
		};
		return false;
	}
	
	public boolean checkCoordinatesInput(String userInput){
		Matcher matcher;
		Pattern pattern = Pattern.compile("\\d*[,]\\d*[-]\\d*[,]\\d*");
		matcher = pattern.matcher(userInput);
		if (!matcher.matches()) {
			System.out.println("Incorrect input.");
			return false;
		}

		String[] points;
		int value1, value2;
		points = userInput.replace(",", "-").split("-");
		value1 = (Integer.valueOf(points[0]) * myBoard.getLineSize())
				+ (Integer.valueOf(points[1]) % myBoard.getLineSize());
		value2 = (Integer.valueOf(points[2]) * myBoard.getLineSize())
				+ (Integer.valueOf(points[3]) % myBoard.getLineSize());
		// System.out.println(value1+" "+value2);
		if (value1 == value2) {
			System.out.println("You selected twice the same card.");
			return false;
		}
		if ((value1 > myBoard.getBoardTotalSquares())
				|| (value2 > myBoard.getBoardTotalSquares())) {
			System.out.println("Input is Out of Board range.");
			return false;
		}
		if ((Integer.valueOf(points[1]) > myBoard.getLineSize())
				|| (Integer.valueOf(points[3]) > myBoard.getLineSize())) {
			System.out.println("Input is Out of Board range.");
			return false;
		}
		int columns = myBoard.getBoardTotalSquares() / myBoard.getLineSize();
		if ((Integer.valueOf(points[0]) > columns)
				|| (Integer.valueOf(points[2]) > columns)) {
			System.out.println("Input is Out of Board range.");
			return false;
		}
		char v1 = myBoard.getValueAt(value1);
		char v2 = myBoard.getValueAt(value2);
		System.out.println(v1 + " and " + v2);
		if(myBoard.getFound(value1) || myBoard.getFound(value1)){
			System.out.println("One or both cards were already discovered.");
			return false;
		}
		
		return myBoard.setMove(value1, value2);
	}

	boolean checkKeyboardInput(String userInput) {
		return checkOptionsInput(userInput) || checkCoordinatesInput(userInput);		
	}	
	
	public void printOptions() {
		System.out.println("Options:");
		System.out.println("- type \""+Settings.buttonForExit+"\" to exit;");
		System.out.println("- type \""+Settings.buttonForBoard+"\" to show currently found board values;");
		System.out.println("- type \""+Settings.buttonForSolution+"\" to show final board values;");
		System.out.println("- type \""+Settings.buttonForMoveList+"\" to show moves list;");
		System.out.println("- type \""+Settings.buttonForOptions+"\" to show this options list;");
		System.out.println("- type \""+Settings.buttonForRestart+"\" to start a new match;");
	}	

}
