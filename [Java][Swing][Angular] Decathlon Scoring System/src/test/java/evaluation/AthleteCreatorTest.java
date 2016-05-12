package evaluation;

import static org.junit.Assert.*;
import org.junit.Test;

import players.Athlete;

public class AthleteCreatorTest {
		
	@Test
	public void shouldCreateAthleteFromString() {
		String line = "Oconnor-5;21.31;623;21.58;2.31;62.64;23.76;20.79;1.20;86.85;7:20.41";
		Athlete athlete = AthleteCreator.createAthlete(line);
		assertEquals(athlete.getName(),"Oconnor-5");
		assertEquals(athlete.getM100Seconds(),"21.31");
		assertEquals(athlete.getLongJumpCentimeters(),"623");
		assertEquals(athlete.getShotPutMeters(),"21.58");
		assertEquals(athlete.getHighJumpCentimeters(),"2.31");
		assertEquals(athlete.getM400Seconds(),"62.64");
		assertEquals(athlete.getM110HurdleSeconds(),"23.76");
		assertEquals(athlete.getDiscusThrowMeters(),"20.79");
		assertEquals(athlete.getPoleVaultCentimeters(),"1.20");
		assertEquals(athlete.getJavelinThrowMeters(),"86.85");
		assertEquals(athlete.getM1500Seconds(),"7:20.41");
	}	
}
