import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class CriminalGenerator3 {
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
	private static String[] addressNames = {"Park", "Lexington", "Hilltop", "Highland", "College", "Mulberry", "Devonshire", "Academy", "Railroad",
		"Main", "Cardinal", "Market", "Ridge", "Cedar", "Spruce"};
	private static String[] addressEnds = {"Drive", "Street", "Avenue", "Court", "Road", "Lane", "Place"};
	private static String[] cityNames = {"Omaha", "San Diego", "St. Louis", "Tampa", "Detroit", "San Jose", "Nashville-Davidson", "Jersey City", "Dallas",
		"Glendale", "Durham", "Memphis", "Lexington-Fayette", "Reno", "Virginia Beach", "El Paso", "North Hempstead", "Hialeah", "Milwaukee", "San Francisco"};
	private static String[] colors = {"Red", "Blue", "Green", "Purple", "Yellow", "Amaranth", "Amber", "Amethyst", "Apricot", "Aquamarine",
		"Azure", "Beige", "Blush", "Bronze", "Boysenberry", "Lilac", "Lion", "Magenta", "Lust", "Mauvelous", "Carmine", "Malachite", "Taupe",
		"Moccasin", "Mulberry", "Onyx", "Olivine", "Orange", "Copper", "Silver", "Patriarch", "Peach", "Platinum", "Rose", "Raspberry",
		"Ruby", "Russet", "Sandstorm", "Scarlet", "Stormcloud", "Sunglow", "Turqoise", "Zaffre", "Xanadu", "Strawberry", "Waterspout", "Gold",
		"Urobilin", "Skobeloff", "Sapphire"};
	private static String[] foods = {"Jambalaya", "Hummus", "Pizza", "Lamb", "Human", "Ketchup", "Guacamole", "Duck", "Donuts", "Burrito", "Broccoli",
		"Venison", "Reuben", "Kiwi", "Meatballs", "Quesadilla", "Linguine", "Lobster", "Halibut", "Pasta", "Spaghetti", "Grapes", "Fondu", "Chocolate", "Grits",
		"Garlic", "Gnocchi", "Eel", "Fajita", "Falafel", "Pepperoni", "Moose", "Skunk", "Milkshake", "Lasagna", "Egg", "Spinach", "Toast", "Butter", "Waffles",
		"Yogurt", "Zucchini", "Quiche", "Pancakes", "Jerky", "Jelly", "Ham", "Hamburger", "Donut", "Dumpling"};
	private static String[] fitness = {"Super fit. Can run one mile in exactly nine minutes and 72 seconds. Can also lift 312 pounds. This person is ugly.",
		"Not fit at all. Seriously obese. Like, we should put this person on an extreme diet. This person is funny.",
		"Mediocre-ly obese. Could lose like 20 pounds and be the hottest prisoner in Block E. This person is weird.",
		"The potbelly on this person is sorta cute, but I feel like they haven't exercised in four years... This person is frightening.",
		"Decently fit. Completely average except for the fact that they're a criminal. This person is average."};
	private static String[] offense = {"Petty misdemeanor", "Misdemeanor", "Gross misdemeanor", "Felony", "Naughty"};
	private static String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA",
		"ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD",
		"TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
	
	// First arg is number of rows to generate
	// Second arg is the output file path
	// Third arg is the seed for the random number generator
	public static void main(String[] args) throws IOException {
		numRows = Long.parseLong(args[0]);
		outputPath = args[1];
		seed = Long.parseLong(args[2]);
		rand = new Random(seed);
		writer = new PrintWriter(new FileWriter(outputPath));
		
		for (int i = 0; i < numRows; ++i) {
			printSSN();
			printNames(3, 3);
			printHeight(60, 82); // 23 options
			printWeight(120, 300); // 37 options
			printAddress();
			printDOB(1952, 49); // 49 options
			printFavoriteColor(3);
			printShoeSize(6, 16); // 11 options
			printYears(1, 50); // 50 options
			printFavoriteFood(3);
			printFitness();
			printEyeColor(3);
			printHairColor(3);
			printIsVegetarian();
			printLevelOfOffense();
			printState(3);
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
	
	private static void printHeight(int minHeight, int maxHeight) {
		int height = rand.nextInt(maxHeight - minHeight + 1) + minHeight;
		writer.write(String.valueOf(height) + "\t");
	}
	
	// Should be in increments of 5
	private static void printWeight(int minHeight, int maxHeight) {
		int numberOfOptions = ((maxHeight - minHeight) / 5) + 1;
		int weight = (rand.nextInt(numberOfOptions) * 5) + minHeight;
		writer.write(String.valueOf(weight) + "\t");
	}
	
	// Generates a random four-digit address number, a random street name, a random street suffix, random city and state, and random zip code
	private static void printAddress() {
		StringBuilder address = new StringBuilder(100);
		int[] random = {rand.nextInt(addressNames.length), rand.nextInt(addressEnds.length), rand.nextInt(cityNames.length), rand.nextInt(states.length)};
		address.append(String.format("%04d", rand.nextInt(10000))); // Four house number digits
		address.append(" " + addressNames[random[0]] + " " + addressEnds[random[1]] + ", " + cityNames[random[2]] + ", " + states[random[3]] + ", ");
		address.append(String.format("%05d", rand.nextInt(100000))); // Five zip code digits
		writer.write(address.toString() + "\t");
	}
	
	// Gets a random month, day, and year (between 1952 and 2000)
	private static void printDOB(int startYear, int options) {
		GregorianCalendar gc = new GregorianCalendar();
		int year = startYear + rand.nextInt(options); // 49 possibilities, from 1952 to 2000 inclusive
		int numDaysOfYear = gc.isLeapYear(year) ? 366 : 365;
		int dayOfYear = 1 + rand.nextInt(numDaysOfYear);
		gc.set(Calendar.YEAR, year);
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		writer.write((gc.get(Calendar.MONTH) + 1) + "/" + gc.get(Calendar.DAY_OF_MONTH) + "/" + gc.get(Calendar.YEAR) + "\t");
	}

	private static void printFavoriteColor(int options) {
		int color = rand.nextInt(options);
		writer.write(colors[color] + "\t"); // Will choose a color
	}
	
	private static void printShoeSize(int minSize, int maxSize) {
		int shoeSize = rand.nextInt(maxSize - minSize + 1) + minSize;
		writer.write(String.valueOf(shoeSize) + "\t");
	}

	private static void printYears(int min, int max) {
		int numOfYears = rand.nextInt(max - min + 1) + min;
		writer.write(String.valueOf(numOfYears) + "\t");
	}
	
	private static void printFavoriteFood(int options) {
		int food = rand.nextInt(options);
		writer.write(foods[food] + "\t"); // Will choose a food
	}
	
	private static void printFitness() {
		int fitnessNum = rand.nextInt(fitness.length);
		writer.write(fitness[fitnessNum] + "\t"); // Will choose a hilarious fitness level
	}

	private static void printEyeColor(int options) {
		int color = rand.nextInt(options);
		writer.write(colors[color] + "\t"); // Will choose a color
	}
	
	private static void printHairColor(int options) {
		int color = rand.nextInt(options);
		writer.write(colors[color] + "\t"); // Will choose a color
	}
	
	private static void printIsVegetarian() {
		writer.write(String.valueOf(rand.nextBoolean()) + "\t");
	}
	
	private static void printLevelOfOffense() {
		int offenseNum = rand.nextInt(offense.length);
		writer.write(offense[offenseNum] + "\t"); // Will choose one of four offense levels
	}
	
	private static void printState(int options) {
		int state = rand.nextInt(options);
		writer.write(states[state]); // Will choose a state
	}
}
