import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MatrixMultithreading {
	
	// Global matrixes needed for threads
	public static int[][] matrixA = new int[0][0];
	public static int[][] matrixB = new int[0][0];
	public static int[][] finalMatrix = new int[0][0];
	
	public static void main(String[] args) {
		String matrixDataFile = new String();
		int[] dimensions = new int[4];
		
		if(args.length > 0) {
			matrixDataFile = args[0];
		} else {
			System.out.println("Please supply a data file to read.");
			System.exit(0);
		}
		
		// run grabData method
		ArrayList<String> matrixData = new ArrayList<String>(grabData(matrixDataFile));
		
		// get Matrix dimensions
		dimensions = matrixDimensions(matrixData);
      
      // Test matrix dimensions to see if they can be multiplied
      if(dimensions[1] != dimensions[2]) {
         System.out.println("These matrices cannot be multiplied.");
         System.exit(0);
      }
      
	}
	
	// method for reading in data from file
	public static ArrayList<String> grabData(String matrixDataFile) {
		ArrayList<String> data = new ArrayList<String>();
		String line = new String();
		
      try{
         Scanner s = new Scanner(new File(matrixDataFile));
         while (s.hasNextLine()){
            data.add(s.nextLine());
         }
         s.close();
      } catch(FileNotFoundException e) {
         e.printStackTrace();
		}
      
      return data;
	}
	
	public static int[] matrixDimensions(ArrayList<String> matrixData) {
      int[] dimensions = new int[4];
      String dimensionsString = matrixData.get(0);
      dimensionsString = dimensionsString.replaceAll("\\D+","");
      
      for(int i = 0; i < dimensionsString.length(); i++) {
         dimensions[i] = Character.getNumericValue(dimensionsString.charAt(i));
      }
		
      return dimensions;
   }
}