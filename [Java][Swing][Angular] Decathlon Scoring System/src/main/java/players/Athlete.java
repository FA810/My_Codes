package players;

public class Athlete {

	private String name;
	private String m100Seconds;
	private String longJumpCentimeters;
	private String shotPutMeters;
	private String highJumpCentimeters;
	private String m400Seconds;
	private String m110HurdleSeconds;
	private String discusThrowMeters;
	private String poleVaultCentimeters;
	private String javelinThrowMeters;
	private String m1500Seconds;

	//name + m100 + lj + sp + hj + m400 + m110 + dt + pv + jt + m1500);
	public Athlete(String name, String m100Seconds, String longJumpCentimeters, String shotPutMeters,
			String highJumpCentimeters, String m400Seconds, String m110HurdleSeconds, String discusThrowMeters,
			String poleVaultCentimeters, String javelinThrowMeters, String m1500Seconds) {
		super();
		this.name = name;
		this.m100Seconds = m100Seconds;
		this.longJumpCentimeters = longJumpCentimeters;
		this.shotPutMeters = shotPutMeters;
		this.highJumpCentimeters = highJumpCentimeters;
		this.m400Seconds = m400Seconds;
		this.m110HurdleSeconds = m110HurdleSeconds;
		this.discusThrowMeters = discusThrowMeters;
		this.poleVaultCentimeters = poleVaultCentimeters;
		this.javelinThrowMeters = javelinThrowMeters;
		this.m1500Seconds = m1500Seconds;
	}

	public String getName() {
		return name;
	}	

	public void setName(String name) {
		this.name = name;
	}

	public String getM100Seconds() {
		return m100Seconds;
	}

	public void setM100Seconds(String m100Seconds) {
		this.m100Seconds = m100Seconds;
	}

	public String getM400Seconds() {
		return m400Seconds;
	}

	public void setM400Seconds(String m400Seconds) {
		this.m400Seconds = m400Seconds;
	}

	public String getM110HurdleSeconds() {
		return m110HurdleSeconds;
	}

	public void setM110HurdleSeconds(String m110HurdleSeconds) {
		this.m110HurdleSeconds = m110HurdleSeconds;
	}

	public String getM1500Seconds() {
		return m1500Seconds;
	}

	public void setM1500Seconds(String m1500Seconds) {
		this.m1500Seconds = m1500Seconds;
	}

	public String getLongJumpCentimeters() {
		return longJumpCentimeters;
	}

	public void setLongJumpCentimeters(String longJumpCentimeters) {
		this.longJumpCentimeters = longJumpCentimeters;
	}

	public String getHighJumpCentimeters() {
		return highJumpCentimeters;
	}

	public void setHighJumpCentimeters(String highJumpCentimeters) {
		this.highJumpCentimeters = highJumpCentimeters;
	}

	public String getPoleVaultCentimeters() {
		return poleVaultCentimeters;
	}

	public void setPoleVaultCentimeters(String poleVaultCentimeters) {
		this.poleVaultCentimeters = poleVaultCentimeters;
	}

	public String getShotPutMeters() {
		return shotPutMeters;
	}

	public void setShotPutMeters(String shotPutMeters) {
		this.shotPutMeters = shotPutMeters;
	}

	public String getDiscusThrowMeters() {
		return discusThrowMeters;
	}

	public void setDiscusThrowMeters(String discusThrowMeters) {
		this.discusThrowMeters = discusThrowMeters;
	}

	public String getJavelinThrowMeters() {
		return javelinThrowMeters;
	}

	public void setJavelinThrowMeters(String javelinThrowMeters) {
		this.javelinThrowMeters = javelinThrowMeters;
	}

	@Override
	public String toString() {
		return String.format(
				"Athlete [name=%s,\n\t m100Seconds=%s,\n\t longJumpCentimeters=%s,\n\t shotPutMeters=%s,\n\t highJumpCentimeters=%s,\n\t m400Seconds=%s,\n\t m110HurdleSeconds=%s,\n\t discusThrowMeters=%s,\n\t poleVaultCentimeters=%s,\n\t javelinThrowMeters=%s,\n\t m1500Seconds=%s]",
				name, m100Seconds, longJumpCentimeters, shotPutMeters, highJumpCentimeters, m400Seconds,
				m110HurdleSeconds, discusThrowMeters, poleVaultCentimeters, javelinThrowMeters, m1500Seconds);
	}

}
