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
	private static String[] colors = {"Red", "Blue", "Green", "Purple", "Yellow", "Amaranth", "Amber", "Amethyst", "Apricot", "Aquamarine",
			"Azure", "Beige", "Blush", "Bronze"};
	
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
			printPlate();
			printMakeAndModel(10, 10); // Use at least 1 and at most 10 for each of these
			writer.println();
		}
		writer.close();
	}

	private static void printSSN() {
		int SSN = rand.nextInt(1000000000); // Should give 9 digits
		writer.write(VIN.toString() + "\t");
	}

	private static void printPlate() {
		int length = 6; // plates will be 6 characters long
		StringBuilder plate = new StringBuilder(length); 
		for (int i = 0; i < 3; ++i) { // First three digits are numbers
			plate.append((char)('0' + rand.nextInt(10))); // Should give 0 through 9
		}
		for (int i = 0; i < 3; ++i) { // Second three digits are letters
			plate.append((char)('A' + rand.nextInt(26))); // Should give A through Z
		}
		writer.write(plate.toString() + "\t");
	}

	private static void printMakeAndModel(int makeOptions, int modelOptions) {
		int make = rand.nextInt(makeOptions);
		int model = rand.nextInt(modelOptions) + 1; // Add one since the make is stored at index 0
		writer.write(makeAndModel[make][0] + "\t" + makeAndModel[make][model] + "\t");
	}
}
