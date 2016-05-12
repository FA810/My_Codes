package evaluation;

import players.Athlete;

public class AthleteDecathlonScoreSheet {
	private Athlete athlete;
	private Integer m100Score;
	private Integer longJumpScore;
	private Integer shotPutScore;
	private Integer highJumpScore;
	private Integer m400Score;
	private Integer m110Score;
	private Integer discusThrowScore;
	private Integer poleVaultScore;
	private Integer javelinThrowScore;
	private Integer m1500Score;
	private Integer Day1Score;
	private Integer Day2Score;
	private Integer TotalScore;
	
	public AthleteDecathlonScoreSheet(Athlete a){
		this.athlete = a;
	}
	
	public Integer getDay1Score() {
		return Day1Score;
	}
	public void setDay1Score(Integer day1Score) {
		Day1Score = day1Score;
	}
	public Integer getDay2Score() {
		return Day2Score;
	}
	public void setDay2Score(Integer day2Score) {
		Day2Score = day2Score;
	}
	public Integer getTotalScore() {
		return TotalScore;
	}
	public void setTotalScore(Integer totalScore) {
		TotalScore = totalScore;
	}
	public String getName() {
		return athlete.getName();
	}
	public Integer getM100Score() {
		return m100Score;
	}
	public void setM100Score(Integer m100Score) {
		this.m100Score = m100Score;
	}
	public Integer getLongJumpScore() {
		return longJumpScore;
	}
	public void setLongJumpScore(Integer longJumpScore) {
		this.longJumpScore = longJumpScore;
	}
	public Integer getShotPutScore() {
		return shotPutScore;
	}
	public void setShotPutScore(Integer shotPutScore) {
		this.shotPutScore = shotPutScore;
	}
	public Integer getHighJumpScore() {
		return highJumpScore;
	}
	public void setHighJumpScore(Integer highJumpScore) {
		this.highJumpScore = highJumpScore;
	}
	public Integer getM400Score() {
		return m400Score;
	}
	public void setM400Score(Integer m400Score) {
		this.m400Score = m400Score;
	}
	public Integer getM110Score() {
		return m110Score;
	}
	public void setM110Score(Integer m110Score) {
		this.m110Score = m110Score;
	}
	public Integer getDiscusThrowScore() {
		return discusThrowScore;
	}
	public void setDiscusThrowScore(Integer discusThrowScore) {
		this.discusThrowScore = discusThrowScore;
	}
	public Integer getPoleVaultScore() {
		return poleVaultScore;
	}
	public void setPoleVaultScore(Integer poleVaultScore) {
		this.poleVaultScore = poleVaultScore;
	}
	public Integer getJavelinThrowScore() {
		return javelinThrowScore;
	}
	public void setJavelinThrowScore(Integer javelinThrowScore) {
		this.javelinThrowScore = javelinThrowScore;
	}
	public Integer getM1500Score() {
		return m1500Score;
	}
	public void setM1500Score(Integer m1500Score) {
		this.m1500Score = m1500Score;
	}
	@Override
	public String toString() {
		return String.format(
				"AthleteDecathlonScoreSheet [\n\t name=%s,\n\t m100Score=%s,\n\t longJumpScore=%s,\n\t shotPutScore=%s,\n\t highJumpScore=%s,\n\t m400Score=%s,\n\t m110Score=%s,\n\t discusThrowScore=%s,\n\t poleVaultScore=%s,\n\t javelinThrowScore=%s,\n\t m1500Score=%s,\n\t Day1Score=%s,\n\t Day2Score=%s,\n\t TotalScore=%s]",
				athlete.getName(), m100Score, longJumpScore, shotPutScore, highJumpScore, m400Score, m110Score, discusThrowScore,
				poleVaultScore, javelinThrowScore, m1500Score, Day1Score, Day2Score, TotalScore);
	}	
}
