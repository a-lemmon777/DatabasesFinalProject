import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CarGenerator {
	private static long numRows;
	private static String outputPath;
	private static long seed;
	private static Random rand;
	private static PrintWriter writer;
	private static char[] vinChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	private static String[][] makeAndModel = {
			{"Ford", "Explorer", "Focus", "Fusion", "Falcon", "Mustang", "Taurus", "Galaxy", "Escape", "Everest", "Edge"},
			{"Chevy", "Avalanche", "Equinox", "Impala", "Malibu", "Captiva", "Traverse", "Cruze", "Aveo", "Camaro", "Corvette"},
			{"Dodge", "Caravan", "Dart", "Avenger", "Durango", "Charger", "Journey", "Coronet", "Challenger", "Viper", "Ram"},
			{"Audi", "A1", "TT", "R8", "A4", "A5", "A6", "A7", "Q3", "Q5", "Q7"},
			{"Buick", "Encore", "Regal GS", "LaCrosse", "Enclave", "Verano", "Century", "Roadmaster", "Somerset", "Electra", "Riviera"},
			{"Honda", "Accord", "Civic", "Fit", "City", "Jazz", "Insight", "Inspire", "Legend", "Odyssey", "Stream"},
			{"Hyundai", "Accent", "Dynasty", "Elantra", "Eon", "Genesis", "Sonata", "Stellar", "Veloster", "Veracruz", "Tiburon"},
			{"Kia", "Cadenza", "Rio", "Picanto", "Rondo", "Borrego", "Forte", "Soul", "Optima", "Carnival", "Spectra"},
			{"Toyota", "Prius", "Camry", "Century", "Avalon", "Crown", "Allion", "Belta", "Matrix", "Vios", "Highlander"},
			{"Nissan", "Altima", "Maxima", "Frontier", "Leaf", "Skyline", "Armada", "Atlas", "Titan", "Elgrand", "Patrol"}
	};
	private static String[] colors = {"Red", "Blue", "Green", "Purple", "Yellow", "Amaranth", "Amber", "Amethyst", "Apricot", "Aquamarine",
			"Azure", "Beige", "Blush", "Bronze", "Boysenberry", "Lilac", "Lion", "Magenta", "Lust", "Mauvelous", "Carmine", "Malachite", "Taupe",
			"Moccasin", "Mulberry", "Onyx", "Olivine", "Orange", "Copper", "Silver", "Patriarch", "Peach", "Platinum", "Rose", "Raspberry",
			"Ruby", "Russet", "Sandstorm", "Scarlet", "Stormcloud", "Sunglow", "Turqoise", "Zaffre", "Xanadu", "Strawberry", "Waterspout", "Gold",
			"Urobilin", "Skobeloff", "Sapphire"};
	private static String[] states= {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA",
			"ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD",
			"TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
	
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
			printVIN();
			printPlate();
			printState(10);
			printColor(5);
			printMakeAndModel(10, 10); // Use at least 1 and at most 10 for each of these
			printYear(10);
			writer.println();
		}
		writer.close();
	}

	private static void printVIN() {
		int length = 17;  // VINs are always 17 characters long
		StringBuilder VIN = new StringBuilder(length);
		for (int i = 0; i < length; ++i) {
			VIN.append(vinChars[rand.nextInt(vinChars.length)]);
		}
		writer.write(VIN.toString() + "\t");
	}

	private static void printPlate() {
		StringBuilder plate = new StringBuilder();
		plate.append(String.format("%03d", rand.nextInt(1000))); // Gets a 3 digit number
		for (int i = 0; i < 3; ++i) { // Second three digits are letters
			plate.append((char)('A' + rand.nextInt(26))); // Should give A through Z
		}
		writer.write(plate.toString() + "\t");
	}

	private static void printState(int options) {
		int state = rand.nextInt(options);
		writer.write(states[state] + "\t"); // Will choose a state
	}

	private static void printColor(int options) {
		int color = rand.nextInt(options);
		writer.write(colors[color] + "\t"); // Will choose a color
	}

	private static void printMakeAndModel(int makeOptions, int modelOptions) {
		int make = rand.nextInt(makeOptions);
		int model = rand.nextInt(modelOptions) + 1; // Add one since the make is stored at index 0
		writer.write(makeAndModel[make][0] + "\t" + makeAndModel[make][model] + "\t");
	}

	private static void printYear(int numYears) {
		int n = rand.nextInt(numYears); // Generates a number 0 through (numYears - 1)
		writer.write(String.valueOf(2015 - n)); // Will make a year that is n years old
	}
}
