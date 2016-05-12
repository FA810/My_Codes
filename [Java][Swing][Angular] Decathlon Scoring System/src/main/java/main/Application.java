package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import evaluation.AthleteCreator;
import evaluation.AthleteDecathlonScoreSheet;
import evaluation.Evaluator;
import evaluation.JSONWriter;
import evaluation.Ranking;
import players.Athlete;

public class Application {

	public static void main(String args[]) {
		String line;
		BufferedReader br = null;
		Evaluator evaluator = new Evaluator();
		Ranking ranking = new Ranking();
		String filename = Settings.filename;
		if(args.length>0){
			filename = args[0];
		}
		try {
			br = new BufferedReader(new FileReader(filename));
			line = br.readLine();
			// an athlete is created from a text line, evaluated and inserted into ranking
			while (line != null) {				
				Athlete at = AthleteCreator.createAthlete(line);
				//System.out.println(at);
				AthleteDecathlonScoreSheet adss = evaluator.evaluate(at);
				//System.out.println(adss);
				ranking.addAthlete(adss);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n*** Final Ranking ***");
		ranking.sortByTotalScore();
		ranking.printRanking();		
		
		new JSONWriter(ranking).writeJson();
		System.out.println("******\n");		
	}

}