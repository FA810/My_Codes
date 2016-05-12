package evaluation;

import main.Settings;
import players.Athlete;

public class AthleteCreator {
	
	// create an Athlete object from a String line separated by tokens.
	public static Athlete createAthlete(String line){
		String[] splitter = line.split(Settings.separator);
		Athlete athlete = new Athlete(splitter[0],splitter[1],splitter[2],splitter[3],splitter[4],splitter[5],splitter[6],splitter[7],splitter[8],splitter[9],splitter[10]);
		return athlete;
	}

}
