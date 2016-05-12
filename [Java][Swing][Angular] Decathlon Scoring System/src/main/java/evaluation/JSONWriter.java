package evaluation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

/**
 * @author fabio
 * 
 * Saving our Ranking and results in JSON format.
 */
public class JSONWriter {
	
	Ranking ranking;
	Gson g;
	
	public JSONWriter(Ranking ranking){
		g = new Gson();
		this.ranking = ranking;
	}
	
	public void writeJson(){
		PrintWriter fout = null;
		try {
			FileWriter f = new FileWriter("angular/app1.js", false);
			fout = new PrintWriter(f);			
			fout.println("(function($scope,$http){var app = angular.module('ranking', [ ]);"
					+ "app.controller('RankingController', function($scope,$http){this.athlete = athletes;});"
					+ "var athletes = [");
			for (int i = 0; i < ranking.getSize(); i++) {
				fout.println(g.toJson(ranking.getAthleteScoreSheet(i)));
				if(i!=ranking.getSize()-1){fout.print(",");}
			}
			fout.println("];})();");
		} catch (IOException e2) {
			System.out.println("writeError");
		} finally {
			if(fout !=null){
				fout.flush();
				fout.close();
			}						
		}
	}
	
}
