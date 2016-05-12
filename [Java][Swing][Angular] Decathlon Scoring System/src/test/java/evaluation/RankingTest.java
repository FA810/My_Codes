package evaluation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import players.Athlete;

public class RankingTest {
	
	String line1 = "Oconnor-5;10.31;623;21.58;2.31;62.64;23.76;20.79;1.20;86.85;7:20.41";
	String line2 = "Armstrong-7;11.31;623;21.58;2.31;62.64;23.76;20.79;1.20;86.85;7:20.41";
	String line3 = "Bennett-27;12.31;623;21.58;2.31;62.64;23.76;20.79;1.20;86.85;7:20.41";
	String line4 = "Regan-68;12.81;623;21.58;2.31;62.64;23.76;20.79;1.20;86.85;7:20.41";
	Evaluator evaluator = new Evaluator();
	Ranking ranking = new Ranking();
	
	@Test
	public void shouldCreateProperRankingSize() {
		ranking = new Ranking();
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line1)));
		assertEquals(1,ranking.getSize());
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line2)));
		assertEquals(2,ranking.getSize());
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line3)));
		assertEquals(3,ranking.getSize());	
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line4)));
		assertEquals(4,ranking.getSize());
	}
	
	@Test
	public void shouldSortProperly() {
		ranking = new Ranking();
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line1)));
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line2)));
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line3)));
		ranking.addAthlete(evaluator.evaluate(AthleteCreator.createAthlete(line4)));
		ranking.sortByTotalScore();
		assertEquals(ranking.getAthleteScoreSheet(0).getName(),"Oconnor-5");
		assertEquals(ranking.getAthleteScoreSheet(1).getName(),"Armstrong-7");
		assertEquals(ranking.getAthleteScoreSheet(2).getName(),"Bennett-27");
		assertEquals(ranking.getAthleteScoreSheet(3).getName(),"Regan-68");
	}

}
