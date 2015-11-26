package com.fabio.memory;

import java.util.Random;

public class Disposition {

	private String sequence = "abcdefghijklmnop".toUpperCase();
	char[] shuffled = (sequence+sequence).toCharArray();
	static Random rand = new Random();
	int otherLine = 8;
	
	public void shuffleDisposition() {
		int rand;		
		for (int i = 0; i < this.shuffled.length; i++) {
			rand = randInt(0, shuffled.length);
			if (i != rand) {
				this.swap(i, rand);
			}
		}	
	}
	
	public char[] getCards() {
		return shuffled;
	}
	
	public void printCards() {
		for (int i = 0; i < this.shuffled.length; i++) {
			System.out.print(this.shuffled[i] + " ");
		}
		System.out.println();
	}
	
	public void printCardsDisposed() {
		for (int i = 0; i < this.shuffled.length; i++) {
			if((i%otherLine == 0) && (i>0)){
				System.out.println();
			}
			System.out.print(this.shuffled[i] + " ");			
		}
		System.out.println();
	}

	private static int randInt(int min, int max) {
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	}

	private void swap(int i, int j) {
		char temp = shuffled[i];
		shuffled[i] = shuffled[j];
		shuffled[j] = temp;
	}

	public static void main(String[] args) {
		Disposition d = new Disposition();
		d.shuffleDisposition();
		d.printCards();
		d.printCardsDisposed();
		d.shuffleDisposition();
		
		d.printCardsDisposed();
		d.shuffleDisposition();
		d.printCardsDisposed();
	}

}
