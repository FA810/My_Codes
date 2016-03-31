package carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import carte.Carta.Seme;

public class CardsCalculator {

	private Stack<Carta> stackCards = new Stack<Carta>();

	private List<ArrayList<Carta>> CardSolutions = new ArrayList<ArrayList<Carta>>();
	GruppoCarte gruppo;

	/** Store the sum of current elements stored in stack */
	private int sumInStackCards = 0;

	public CardsCalculator(GruppoCarte gruppo) {
		this.gruppo = gruppo;
		this.stackCards = new Stack<Carta>();
	}
	
	public List<ArrayList<Carta>> findAdditionCards(Carta target) {
		return findAdditionCards(target.getValore());
	}
	
	public List<ArrayList<Carta>> findAdditionCards(int target) {
		List<Carta> initial = this.gruppo.getCarte();
		List<Carta> carte = new ArrayList<Carta>();
		for(Carta c : initial){
			if(c.getValore()<=target){
				carte.add(c);
			}
		}
		this.CardSolutions = new ArrayList<ArrayList<Carta>>();
		this.CardSolutions = populateSubsetCards(carte, target, 0, carte.size());
		return CardSolutions;
	}

	private List<ArrayList<Carta>> populateSubsetCards(List<Carta> data2, int target, int fromIndex, int endIndex) {

		if (sumInStackCards == target) {
			ArrayList<Carta> cardList = new ArrayList<Carta>();
			for (Carta i : stackCards) {
				cardList.add(i);
			}
			CardSolutions.add(cardList);

			return CardSolutions;
		}

		for (int currentIndex = fromIndex; currentIndex < endIndex; currentIndex++) {

			if (sumInStackCards + data2.get(currentIndex).getValore() <= target) {
				stackCards.push(data2.get(currentIndex));
				sumInStackCards += data2.get(currentIndex).getValore();

				/*
				 * Make the currentIndex +1, and then use recursion to proceed
				 * further.
				 */
				populateSubsetCards(data2, target, currentIndex + 1, endIndex);
				sumInStackCards -= (Integer) stackCards.pop().getValore();
			}
		}
		return CardSolutions;
	}

	/**
	 * Print satisfied result. i.e. 15 = 4+6+5
	 */
	private void printCardsSolution() {
		for (ArrayList<Carta> j : this.CardSolutions) {
			for (Carta i : j) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public List<Carta> checkSameCards(Carta carta, Tavolo tavolo) {
		List<Carta> result = new ArrayList<Carta>();
		for (Carta c : tavolo.getCarte()) {
			if (carta.getValore() == c.getValore()) {
				result.add(c);
			}
		}
		return result;
	}

	public static void main(String args[]) {

		List<Carta> cards = new ArrayList<Carta>();
		// cards.add(new Carta(Seme.COPPE, 1));
		cards.add(new Carta(Seme.MAZZE, 1));
		cards.add(new Carta(Seme.MAZZE, 2));
		// cards.add(new Carta(Seme.SPADE, 1));
		// cards.add(new Carta(Seme.SPADE, 2));
		cards.add(new Carta(Seme.SPADE, 3));
		Tavolo tavolo = new Tavolo();
		tavolo.setCarte(cards);
		CardsCalculator ccalc = new CardsCalculator(tavolo);
		ccalc.findAdditionCards(6);
		ccalc.printCardsSolution();
		
		
		System.out.println("find");
		cards = new ArrayList<Carta>();
		// cards.add(new Carta(Seme.COPPE, 1));
		cards.add(new Carta(Seme.MAZZE, 1));
		cards.add(new Carta(Seme.MAZZE, 2));
		// cards.add(new Carta(Seme.SPADE, 1));
		cards.add(new Carta(Seme.SPADE, 2));
		cards.add(new Carta(Seme.SPADE, 3));
		//tavolo = new Tavolo();
		tavolo.setCarte(cards);
		ccalc = new CardsCalculator(tavolo);
		System.out.println(ccalc.findAdditionCards(3).get(0));
		ccalc.printCardsSolution();
	}
}
