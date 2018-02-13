/*
** Course:     CS4345
** Semester:   Spring 2018
** Assignment: Program 1
** Author:     Brandon Corbett
*/

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
      int[] dimArr1 = new int[2];
      int[] dimArr2 = new int[2];
      int[] dimArr3 = new int[2];
		
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
      dimArr1[0] = dimensions[0];
      dimArr1[1] = dimensions[1];
      dimArr2[0] = dimensions[2];
      dimArr2[1] = dimensions[3];
      dimArr3[0] = dimensions[0];
      dimArr3[1] = dimensions[3];
      
      // temp arrays before cloning with correct dimensions
      int[][] tempA = new int[dimensions[0]][dimensions[1]];
      int[][] tempB = new int[dimensions[2]][dimensions[3]];
      
      // build the first matrix
      matrixData.remove(0);
      matrixData.remove(0);
      tempA = buildMatrix(matrixData, tempA, dimArr1);
      
      // remove lines from matrixData to build second matrix
      for(int i = 0; i <= tempA.length; i++) {
         matrixData.remove(0);
      }
      
      // build the second matrix
      tempB = buildMatrix(matrixData, tempB, dimArr2);
      
      // clone matrixes to global arrays 
      matrixA = tempA.clone();
      matrixB = tempB.clone();
      
      // build finalMatrix to clone to global
      int[][] fm = new int[dimArr3[0]][dimArr3[1]];
      finalMatrix = fm.clone();
      
      //printMatrices(matrixA, dimArr1);
      //System.out.println();
      //printMatrices(matrixB, dimArr2);
      
      // ArrayList<Thread> for storing thread objects 
      ArrayList<Thread> coolThreads = new ArrayList<Thread>();
      
      // loop through finalMatrix to set values for objects in coolThreads
      int threadCount = 0;
      for(int i = 0; i < finalMatrix.length; i++) {
         for(int j = 0; j < finalMatrix[i].length; j++) {
            ThreadClass tc = new ThreadClass(i, j);
            coolThreads.add(new Thread(tc));
         }
      }
      
      // start multithreading
      for(int i = 0; i < coolThreads.size(); i++) {
         coolThreads.get(i).start();
      }
      
      // sleep the main thread while others are running
      try {
         Thread.sleep(4000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      
      // print final matrix
      System.out.println();
      System.out.println("----------");
      System.out.println();
      printMatrices(finalMatrix, dimArr3);
	}
	
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
      
      if(dimensions[1] != dimensions[2]) {
         System.out.println("These matrices cannot be multiplied.");
         System.exit(0);
      }
		
      return dimensions;
   }
   
   public static int[][] buildMatrix(ArrayList<String> matrixData, int[][] tempMatrix, int[] dimensions) {
      int[][] builtMatrix = new int[dimensions[0]][dimensions[1]];
      
      for (int i = 0; i < tempMatrix.length; i++) {
			int counter = 0;
			String[] nums = matrixData.get(i).split("\\s+");
			for (int j = 0; j < nums.length; j++) {
            try {
				   builtMatrix[i][j] = Integer.parseInt(nums[j]);
            } catch (NumberFormatException e) {
               e.printStackTrace();
            }
			}
		}
      
      return builtMatrix;
   }
   
   public static void printMatrices(int[][] matrices, int[] dimensions) {
      for(int i = 0; i < matrices.length; i++) {
         for(int j = 0; j < dimensions[1]; j++) {
            System.out.print(matrices[i][j] + "\t");
         }
         System.out.println();
      }
   }
}

class ThreadClass extends Thread {
   private int a, b;
   
   public ThreadClass(int a, int b) {
      this.a = a;
      this.b = b;
   }
   
   public void run() {
      System.out.println("Starting multiplication for thread [" + a + ", " + b + "]");
      matrixMultiplication();
      System.out.println("Thread [" + a + ", " + b + "] result: " + MatrixMultithreading.finalMatrix[a][b]);
   }
   
   public void matrixMultiplication() {
      int result = 0;
      for (int i = 0; i < MatrixMultithreading.matrixA[a].length; i++) {
			result += MatrixMultithreading.matrixA[a][i] * MatrixMultithreading.matrixB[i][b];
      }

		MatrixMultithreading.finalMatrix[a][b] = result;
    }
}