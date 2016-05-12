package evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {

	private List<AthleteDecathlonScoreSheet> rank = new ArrayList<AthleteDecathlonScoreSheet>();
	
	public void printRanking() {
		for (AthleteDecathlonScoreSheet temp : rank) {
			System.out.println(temp.getName() + " - " + temp.getTotalScore());
		}
	}
	
	public void addAthlete(AthleteDecathlonScoreSheet athleteScore) {
		rank.add(athleteScore);
	}
	
	public AthleteDecathlonScoreSheet getAthleteScoreSheet(int index) {
		return rank.get(index);
	}
	
	public int getSize() {
		return rank.size();
	}
	
	public void sortByTotalScore() {
		Collections.sort(rank, totalScoreComparator);
	}
	
	public void sortByDay1Score() {
		Collections.sort(rank, day1ScoreComparator);
	}
	
	public void sortByDay2Score() {
		Collections.sort(rank, day2ScoreComparator);
	}

	// from here starts a series of comparators
	public static final Comparator<AthleteDecathlonScoreSheet> totalScoreComparator = new Comparator<AthleteDecathlonScoreSheet>() {
		public int compare(AthleteDecathlonScoreSheet a1, AthleteDecathlonScoreSheet a2) {
			int difference = Integer.compare(a1.getTotalScore(), a2.getTotalScore());
			return -difference;
		}
	};	
	
	public static final Comparator<AthleteDecathlonScoreSheet> day1ScoreComparator = new Comparator<AthleteDecathlonScoreSheet>() {
		public int compare(AthleteDecathlonScoreSheet a1, AthleteDecathlonScoreSheet a2) {
			int difference = Integer.compare(a1.getDay1Score(), a2.getDay1Score());
			return -difference;
		}
	};
	
	public static final Comparator<AthleteDecathlonScoreSheet> day2ScoreComparator = new Comparator<AthleteDecathlonScoreSheet>() {
		public int compare(AthleteDecathlonScoreSheet a1, AthleteDecathlonScoreSheet a2) {
			int difference = Integer.compare(a1.getDay2Score(), a2.getDay2Score());
			return -difference;
		}
	};
	
	// ... and many more other comparators can be created

}
