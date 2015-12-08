import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CriminalGenerator {
	private static long numRows;
	private static String outputPath;
	private static long seed;
	private static Random rand;
	private static PrintWriter writer;
	private static String[] firstNames = {"Eva", "Victor", "Vincent", "Darrel", "Michael", "Bessie", "Eduardo", "Charlene", "Maggie", "Bobby",
		"Shawna", "Lee", "Shelly", "Jessica", "Erma", "Faith", "Jeannie", "Ernestine", "Clay", "Thomas", "Tony", "Jacob", "Sarah", "Jack",
		"Oscar", "Roberto", "Clayton", "Ruben", "Shaun", "Sonja", "Christie", "Francis", "Katherine", "Santos", "Wm", "Lela", "Leticia", "Gina",
		"Rolando", "Wilbert", "Yvonne", "Jason", "Judy", "Lynne", "Hubert", "Penny", "Eric", "Lyle", "Hugh", "Fannie"};
	private static String[] lastNames = {"Cobb", "Walsh", "Fisher", "Wade", "Jennings", "Underwood", "Holland", "Diaz", "Warren", "Lowe", "Phillips",
		"Mills", "McCarthy", "Murray", "Ray", "Chapman", "Carson", "Caldwell", "Summers", "Hill", "Parsons", "Baker", "Lewis", "Stone", "Vasquez",
		"Jimenez", "McBride", "Gilbert", "Vargas", "Neal", "Bowers", "Tucker", "Daniel", "Hardy", "McCoy", "Clayton", "Ryan", "Maldonado", "Alvarado",
		"Nash", "Mathis", "Park", "Castro", "Green", "Wong", "Wright", "Miles", "Armstrong", "Collins"};
	private static String[] colors = {"Red", "Blue", "Green", "Purple", "Yellow", "Amaranth", "Amber", "Amethyst", "Apricot", "Aquamarine",
		"Azure", "Beige", "Blush", "Bronze", "Boysenberry", "Lilac", "Lion", "Magenta", "Lust", "Mauvelous", "Carmine", "Malachite", "Taupe",
		"Moccasin", "Mulberry", "Onyx", "Olivine", "Orange", "Copper", "Silver", "Patriarch", "Peach", "Platinum", "Rose", "Raspberry",
		"Ruby", "Russet", "Sandstorm", "Scarlet", "Stormcloud", "Sunglow", "Turqoise", "Zaffre", "Xanadu", "Strawberry", "Waterspout", "Gold",
		"Urobilin", "Skobeloff", "Sapphire"};
	
	// First arg is number of rows to generate
	// Second arg is the output file path
	// Third arg is the seed for the random number generator
	public static void main(String[] args) throws IOException {
		numRows = Integer.parseInt(args[0]);
		outputPath = args[1];
		seed = Long.parseLong(args[2]);
		rand = new Random(seed);
		writer = new PrintWriter(new FileWriter(outputPath));
		
		for (int i = 0; i < numRows; ++i) {
			printSSN();
			printNames(10, 10);
//			printHeight();
//			printWeight();
//			printAddress();
//			printDOB();
			printFavoriteColor(10);
//			printShoeSize();
//			printYears();
//			printFavoriteFood();
//			printFitness();
//			printEyeColor();
//			printHairColor();
//			printIsVegetarian();
//			printLevelOfOffense();
//			printState();
			writer.println();
		}
		writer.close();
	}

	private static void printSSN() {
		int SSN = rand.nextInt(1000000000); // Should give 9 digits
		writer.write(String.format("%09d", SSN) + "\t");
	}

	private static void printNames(int firstOptions, int lastOptions) {
		int first = rand.nextInt(firstOptions);
		int last = rand.nextInt(lastOptions);
		writer.write(firstNames[first] + "\t" + lastNames[last] + "\t"); // Randomly chooses a first and last name
	}

	private static void printFavoriteColor(int options) {
		int color = rand.nextInt(options);
		writer.write(colors[color] + "\t"); // Will choose a color
	}
}
