package carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GruppoCarte {

	private List<Carta> carte;

	List<Carta> scope = new ArrayList<Carta>();

	public List<Carta> getScope() {
		return scope;
	}

	public void setScope(Carta scopa) {
	}

	public GruppoCarte() {
		carte = new ArrayList<Carta>();
	}

	public List<Carta> getCarte() {
		return carte;
	}

	public void setCarte(List<Carta> carte) {
		this.carte = carte;
	}

	public void printSemePerRiga() {
		if (carte.size() == 0)
			return;
		this.sortBySeme();
		String nextRow = "";
		Carta.Seme prev = carte.get(0).getSeme();
		for (Carta temp : carte) {
			if (temp.getSeme() != prev) {
				prev = temp.getSeme();
				nextRow = "\n";
			} else {
				if (temp != carte.get(0)) {
					nextRow = " - ";
				}
			}
			System.out.print(nextRow + (temp.getSeme()) + " " + temp.getValore());
		}
		System.out.println();
	}

	public void printValorePerRiga() {
		if (carte.size() == 0)
			return;
		this.sortByValore();
		String nextRow = "";
		int prev = carte.get(0).getValore();
		for (Carta temp : carte) {
			if (temp.getValore() != prev) {
				prev = temp.getValore();
				nextRow = "\n";
			} else {
				if (temp != carte.get(0)) {
					nextRow = " - ";
				}
			}
			System.out.print(nextRow + (temp.getSeme()) + " " + temp.getValore());
		}
		System.out.println();
	}

	public void printPrimieraPerRiga() {
		if (carte.size() == 0)
			return;
		this.sortByPrimiera();
		String nextRow = "";
		int prev = Primiera.getPrimieraValue(carte.get(0).getValore());
		for (Carta temp : carte) {
			if (Primiera.getPrimieraValue(temp.getValore()) != prev) {
				prev = Primiera.getPrimieraValue(temp.getValore());
				nextRow = "\n";
			} else {
				if (temp != carte.get(0)) {
					nextRow = " - ";
				}
			}
			System.out.print(nextRow + (temp.getSeme()) + " " + temp.getValore());
		}
		System.out.println();
	}

	public int getSize() {
		return carte.size();
	}

	public void printCarte() {
		for (Carta temp : carte) {
			System.out.println((temp.getSeme()) + " " + temp.getValore());
		}
	}

	public void sort(Comparator<Carta> comparator) {
		Collections.sort(carte, comparator);
	}

	public void sortBySeme() {
		Collections.sort(carte, semeComparator);
	}

	public void sortByValore() {
		Collections.sort(carte, valoreComparator);
	}

	public void sortByPrimiera() {
		Collections.sort(carte, primieraComparator);
	}

	public static final Comparator<Carta> semeComparator = new Comparator<Carta>() {
		public int compare(Carta c1, Carta c2) {
			int semeDifferenza = Integer.compare(c1.getSeme().getCode(), c2.getSeme().getCode());
			if (semeDifferenza != 0) {
				return semeDifferenza;
			} else {
				return Integer.compare(c1.getValore(), c2.getValore());
			}
		}
	};

	public static final Comparator<Carta> valoreComparator = new Comparator<Carta>() {
		public int compare(Carta c1, Carta c2) {
			int valDifferenza = Integer.compare(c1.getValore(), c2.getValore());
			if (valDifferenza != 0) {
				return valDifferenza;
			} else {
				return Integer.compare(c1.getSeme().getCode(), c2.getSeme().getCode());
			}
		}
	};

	public static final Comparator<Carta> primieraComparator = new Comparator<Carta>() {
		public int compare(Carta c1, Carta c2) {
			return -1 * Integer.compare(c1.getPrimieraValue(), c2.getPrimieraValue());
		}
	};

	public void addCard(Carta carta) {
		this.carte.add(carta);
	}

	public void addCard(Carta.Seme seme, int valore) {
		this.carte.add(new Carta(seme, valore));
	}

	public void removeCards(List<Carta> carte) {
		for (Carta carta : carte) {
			removeCard(carta.getValore(), carta.getSeme());
		}
	}

	public void removeAllCards() {
		carte = new ArrayList<Carta>();
	}

	public void removeCard(Carta carta) {
		removeCard(carta.getValore(), carta.getSeme());
	}

	public void removeCard(int valore, Carta.Seme seme) {
		for (Carta tempCarta : carte) {
			if ((seme == tempCarta.getSeme()) && (valore == tempCarta.getValore())) {
				this.carte.remove(tempCarta);
				return;
			}
		};
	}

	public Carta removeCardAt(int index) {
		Carta result = carte.get(index);
		this.carte.remove(result);
		return result;
	}

	public Carta isCartaContained(int valore) {
		this.sortByValore();
		for (Carta tempCarta : carte) {
			if (tempCarta.getValore() == valore)
				return tempCarta;
			if (tempCarta.getValore() > valore)
				return null;
		}
		return null;
	}

	public Carta isCartaContained(Carta.Seme seme, int valore) {
		this.sortByValore();
		for (Carta tempCarta : carte) {
			if ((tempCarta.getValore() == valore) && (tempCarta.getSeme() == seme))
				return tempCarta;
		}
		return null;
	}

	public void addCards(List<Carta> carte) {
		this.carte.addAll(carte);
	}

	public int findPrimiera() {
		int denari = 0, mazze = 0, spade = 0, coppe = 0;
		for (Carta tempCarta : carte) {
			switch (tempCarta.getSeme()) {
			case COPPE:
				coppe = Math.max(coppe, tempCarta.getPrimieraValue());
				break;
			case DENARI:
				denari = Math.max(denari, tempCarta.getPrimieraValue());
				break;
			case SPADE:
				spade = Math.max(spade, tempCarta.getPrimieraValue());
				break;
			case MAZZE:
				mazze = Math.max(mazze, tempCarta.getPrimieraValue());
				break;
			}
			;
		}
		// System.out.println(mazze+" "+coppe+" "+spade+" "+denari);
		if (mazze > 0 && coppe > 0 && spade > 0 & denari > 0) {
			return mazze + coppe + spade + denari + 100;
		} else
			return mazze + coppe + spade + denari;
	}

}
