package generator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import main.Settings;

/**
 *  This class is for generating a file with athletes' names and their result in each discipline.
 */
public class FakeAthleteGenerator {	

	private String[] lastnames = new String[] { "Abbott", "Acevedo", "Acosta", "Adams", "Adkins", "Aguilar", "Aguirre",
			"Albert", "Alexander", "Alford", "Allen", "Allison", "Alston", "Alvarado", "Alvarez", "Anderson", "Andrews",
			"Anthony", "Armstrong", "Arnold", "Ashley", "Atkins", "Atkinson", "Austin", "Avery", "Avila", "Ayala",
			"Benjamin", "Bennett", "Benson", "Bentley", "Benton", "Berg", "Berger", "Bernard", "Berry", "Best", "Bird",
			"Bishop", "Black", "Blackburn", "Blackwell", "Blair", "Blake", "Blanchard", "Blankenship", "Blevins",
			"Bolton", "Bond", "Bonner", "Booker", "Boone", "Booth", "Bowen", "Bowers", "Bowman", "Boyd", "Boyer",
			"Boyle", "Bradford", "Bradley", "Bradshaw", "Brady", "Branch", "Bray", "Brennan", "Brewer", "Bridges",
			"Briggs", "Bright", "Britt", "Brock", "Brooks", "Brown", "Browning", "Bruce", "Bryan", "Bryant", "Buchanan",
			"Buck", "Buckley", "Buckner", "Bullock", "Burch", "Burgess", "Burke", "Burks", "Burnett", "Burns", "Burris",
			"Casey", "Cash", "Castaneda", "Castillo", "Castro", "Cervantes", "Chambers", "Chan", "Chandler", "Chaney",
			"Chang", "Chapman", "Charles", "Chase", "Chavez", "Chen", "Cherry", "Christensen", "Christian", "Church",
			"Clark", "Clarke", "Clay", "Clayton", "Clements", "Clemons", "Cleveland", "Cline", "Cobb", "Cochran",
			"Coffey", "Cohen", "Cole", "Coleman", "Collier", "Collins", "Colon", "Combs", "Compton", "Conley", "Conner",
			"Conrad", "Contreras", "Conway", "Cook", "Cooke", "Cooley", "Cooper", "Copeland", "Cortez", "Cote",
			"Gonzales", "Gonzalez", "Good", "Goodman", "Goodwin", "Gordon", "Gould", "Graham", "Grant", "Graves",
			"Gray", "Green", "Greene", "Greer", "Gregory", "Griffin", "Griffith", "Grimes", "Gross", "Guerra",
			"Harrell", "Harrington", "Harris", "Harrison", "Hart", "Hartman", "Harvey", "Hatfield", "Hawkins", "Hayden",
			"Hayes", "Haynes", "Hays", "Head", "Heath", "Hebert", "Henderson", "Hendricks", "Hendrix", "Henry",			
			"Lane", "Lang", "Langley", "Lara", "Larsen", "Larson", "Lawrence", "Lawson", "Le", "Leach", "Leblanc",
			"Lee", "Leon", "Leonard", "Lester", "Levine", "Levy", "Lewis", "Lindsay", "Lindsey", "Little", "Livingston",
			"Lloyd", "Logan", "Long", "Lopez", "Lott", "Love", "Lowe", "Lowery", "Lucas", "Luna", "Lynch", "Lynn",
			"Lyons", "Macdonald", "Macias", "Mack", "Madden", "Maddox", "Maldonado", "Malone", "Mann", "Manning",
			"Marks", "Marquez", "Marsh", "Marshall", "Martin", "Martinez", "Mason", "Massey", "Mathews", "Mathis",
			"Mckay", "Mckee", "Mckenzie", "Mckinney", "Mcknight", "Mclaughlin", "Mclean", "Mcleod", "Mcmahon",
			"Mcmillan", "Mcneil", "Mcpherson", "Meadows", "Medina", "Mejia", "Melendez", "Melton", "Mendez", "Mendoza",
			"Mercado", "Mercer", "Merrill", "Merritt", "Meyer", "Meyers", "Michael", "Middleton", "Miles", "Miller",			
			"Porter", "Potter", "Potts", "Powell", "Powers", "Pratt", "Preston", "Price", "Prince", "Pruitt", "Puckett",
			"Pugh", "Quinn", "Ramirez", "Ramos", "Ramsey", "Randall", "Randolph", "Rasmussen", "Ratliff", "Ray",
			"Rodriguez", "Rodriquez", "Rogers", "Rojas", "Rollins", "Roman", "Romero", "Rosa", "Rosales", "Rosario",
			"Rose", "Ross", "Roth", "Rowe", "Rowland", "Roy", "Ruiz", "Rush", "Russell", "Russo", "Rutledge", "Ryan",
			"Salas", "Salazar", "Salinas", "Sampson", "Sanchez", "Sanders", "Sandoval", "Sanford", "Santana",
			"Santiago", "Santos", "Sargent", "Saunders", "Savage", "Sawyer", "Schmidt", "Schneider", "Schroeder",
			"Valentine", "Valenzuela", "Vance", "Vang", "Vargas", "Vasquez", "Vaughan", "Vaughn", "Vazquez", "Vega",
			"Velasquez", "Velazquez", "Velez", "Villarreal", "Vincent", "Vinson", "Wade", "Wagner", "Walker", "Wall",
			"Wallace", "Waller", "Walls", "Walsh", "Walter", "Walters", "Walton", "Ward", "Ware", "Warner", "Warren",			
			"Williams", "Williamson", "Willis", "Wilson", "Winters", "Wise", "Witt", "Wolf", "Wolfe", "Wong", "Wood",
			"Woodard", "Woods", "Woodward", "Wooten", "Workman", "Wright", "Wyatt", "Wynn", "Yang", "Yates", "York",
			"Young", "Zamora", "Zimmerman" };

	private static Random rand = new Random();
	private static boolean manualTiming = false;

	public String generateAthlete() {		
		
		int decimalForTiming = 99;
		if(manualTiming){decimalForTiming = 9;}
		StringBuilder sb = new StringBuilder();
		String sep = Settings.separator;
		String name = lastnames[randInt(0, lastnames.length)] + "-" + randInt(0, 80);
		String m100 = sep + randNum(10, 22) + "." + randNum(0, decimalForTiming);
		String lj = sep + randNum(225, 951);
		String sp = sep + randNum(1, 24) + "." + randNum(0, 99);
		String hj = sep + randNum(0, 3) + "." + randNum(0, 99);
		String m400 = sep + randNum(41, 82) + "." + randNum(0, decimalForTiming);
		String m110 = sep + randNum(12, 29) + "." + randNum(0, decimalForTiming);
		String dt = sep + randNum(4, 81) + "." + randNum(0, 99);
		String pv = sep + randNum(1, 7) + "." + randNum(0, 99);
		String jt = sep + randInt(5, 103) + "." + randNum(0, 99);
		String m1500 = sep + randNum(3, 9) + ":" + randNum(0, 60) + "." + randNum(0, 99);
		sb.append(name + m100 + lj + sp + hj + m400 + m110 + dt + pv + jt + m1500);
		return sb.toString();
	}
		
	private static int randInt(int min, int max) {
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	}
	
	// Generates a random number as String, with maximum digits from max number.
	private static String randNum(int min, int max) {
		int numberOfDigits = String.valueOf(max).length();
		int randomNum = randInt(min,max);		
		return String.format("%0"+numberOfDigits+"d", randomNum).substring(0,numberOfDigits);
	}	

	public static void main(String args[]) {
		int toGenerate = Settings.toGenerate;
		if(args.length>0){
			toGenerate = Integer.parseInt(args[0]);
		}		
		FakeAthleteGenerator ag = new FakeAthleteGenerator();		
		System.out.println("\n\nGenerating athletes...\n");
		
		PrintWriter fout = null;
		
		// write the file with decathlon results
		try {
			FileWriter f = new FileWriter(Settings.filename, false);
			fout = new PrintWriter(f);
			
			for (int i = 0; i < toGenerate; i++) {
				String tmp = ag.generateAthlete();
				System.out.println(tmp);
				fout.println(tmp);
			}
		} catch (IOException e2) {
			System.out.println("writeError");
		} finally {
			if(fout !=null){
				fout.flush();
				fout.close();
			}						
		}
		System.out.println("\nDone. \n");
	}

}
