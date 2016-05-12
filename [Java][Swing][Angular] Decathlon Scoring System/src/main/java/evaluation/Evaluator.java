package evaluation;

import measures.*;
import players.Athlete;

/**
 * This class is for evaluating athletes' performances and assigning them scores.
 */
public class Evaluator {
	
	public AthleteDecathlonScoreSheet evaluate(Athlete athlete){
		AthleteDecathlonScoreSheet adss = new AthleteDecathlonScoreSheet(athlete);
		Integer m100Score = new M100Measure().getEvaluation(athlete.getM100Seconds());
		Integer ljScore = new LongJumpMeasure().getEvaluation(athlete.getLongJumpCentimeters());
		Integer spScore = new ShotPutMeasure().getEvaluation(athlete.getShotPutMeters());
		Integer hjScore = new HighJumpMeasure().getEvaluation(athlete.getHighJumpCentimeters());
		Integer m400Score = new M400Measure().getEvaluation(athlete.getM400Seconds());
		Integer m110Score = new M110HurdleMeasure().getEvaluation(athlete.getM110HurdleSeconds());
		Integer dtScore = new DiscusThrowMeasure().getEvaluation(athlete.getDiscusThrowMeters());
		Integer pvScore = new PoleVaultMeasure().getEvaluation(athlete.getPoleVaultCentimeters());
		Integer jtScore = new JavelinThrowMeasure().getEvaluation(athlete.getJavelinThrowMeters());
		Integer m1500Score = new M1500Measure().getEvaluation(athlete.getM1500Seconds());
		adss.setM100Score(m100Score);
		adss.setLongJumpScore(ljScore);
		adss.setShotPutScore(spScore);
		adss.setHighJumpScore(hjScore);
		adss.setM400Score(m400Score);
		adss.setM110Score(m110Score);
		adss.setDiscusThrowScore(dtScore);
		adss.setPoleVaultScore(pvScore);
		adss.setJavelinThrowScore(jtScore);
		adss.setM1500Score(m1500Score);
		adss.setDay1Score(m100Score+ljScore+spScore+hjScore+m400Score);
		adss.setDay2Score(m110Score+dtScore+pvScore+jtScore+m1500Score);
		adss.setTotalScore(adss.getDay1Score()+adss.getDay2Score());
		return adss;
	}

}
