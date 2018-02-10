import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class MatrixMultithreading {
	
	// Global matrixes needed for threads
	public static int[][] matrixA = new int[0][0];
	public static int[][] matrixB = new int[0][0];
	public static int[][] finalMatrix = new int[0][0];
	
	public static void main(String[] args) {
		ArrayList<String> matrixData = new ArrayList<String>();
		String matrixDataFile = new String();
		String dimensions = new String();
		
		if(args.length > 0) {
			matrixDataFile = args[0];
		} else {
			System.out.println("Please supply a data file to read.");
			System.exit(0);
		}
		
		// run grabData method
		matrixData = grabData(matrixDataFile);
		
		// get Matrix dimensions
		dimensions = matrixDimensions(matrixData);
	}
	
	// method for reading in data from file
	public static ArrayList<String> grabData(String matrixDataFile) {
		ArrayList<String> data = new ArrayList<String>();
		String line = new String();
		try {
			//FileReader file = new FileReader(matrixDataFile);
			BufferedReader reader = new BufferedReader(new FileReader(matrixDataFile));
			while ((line = reader.readLine()) != null) {
				data.add(line);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static String matrixDimensions(ArrayList<String> matrixData) {
		String dimensions = new String();
		dimensions = matrixData.get(0);
		
		return dimensions;
	}
}