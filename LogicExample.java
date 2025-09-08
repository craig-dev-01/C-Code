/* Craig 
 * 
 * 
 * Description: This program generates two random ints, size1 and size2. Then writes(size1+size2)
 * random numbers between 0 and 49 to a file "assignment1.txt". Reads the file loading numbers into 
 * two arrays. Then, sorts the arrays individually and then combines the two arrays while simultaneously sorting 
 * the combined array from smallest to largest values manually. 
 */

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogicExample {

	public static void main(String[] args) {
		
		//Generate random size1 and size2 range 0-15
		int size1 = (int)(16*Math.random());
		int size2 = (int)(16*Math.random());
		
		// initialize arrays
		int[] array1 = new int[size1];
		int[] array2 = new int[size2];
		int[] arraysCombined = new int[(size1 + size2)];
		
		//Display size1 and size2
		System.out.printf("Size1 = %d and Size2 = %d \n\n", size1,size2);
		
		//create assignment.txt:
		File file = new File("assignment.txt");
		
		//write (size1+size2) numbers to assignment.txt using random range 0-49
		try {
			
			PrintWriter outputFile = new PrintWriter(file);
			
			
			//print values written to assignment.txt
			System.out.printf("Wrote following values to file: ");
			
			for(int i = 0; i < (size1+size2); i++) {
				int value = (int)(49*Math.random());
				outputFile.println(value);
				System.out.printf("%d ", value);
			}
			System.out.println();
			
			outputFile.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		//Read numbers from assignment.txt and load array1 and array2
		try{
			Scanner inputFile = new Scanner(file);
			//variables for counting number of ints read from file
			int counter1 = 0;
			int counter2 = 0;
			
			//load array1:
			for(int i = 0; i < size1; i++) {
				array1[i] = inputFile.nextInt();
				counter1++;
			}
			
			//load array2:
			for(int i = 0; i < size2; i++) {
				array2[i] = inputFile.nextInt();
				counter2++;
			}
		
			inputFile.close();
			
			//Display values read:
			System.out.printf("Loaded %d values into array1.\nLoaded %d values into array2.\n\n", counter1, counter2);
		
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
		
		//Sort arrays: 
		Arrays.sort(array1);
		Arrays.sort(array2);
		
		//Display array1
		System.out.print("Array1: ");
		for(int i = 0; i < array1.length; i++) {
		    if(i == array1.length - 1) {
		        System.out.print(array1[i]); 
		    } else {
		        System.out.print(array1[i] + ", ");
		    }
		}
		System.out.println();
		
		//Display array2
		System.out.print("Array2: ");
		for(int i = 0; i < array2.length; i++) {
		    if(i == array2.length - 1) {
		        System.out.print(array2[i]); 
		    } else {
		        System.out.print(array2[i] + ", ");
		    }
		}
		System.out.println();
		
		//Combine Arrays into one
		combineAndSortArrays(array1, array2, arraysCombined, size1, size2);
		
		//print out the combined array
		System.out.print("New Sorted and Merged Array: ");
		for(int i = 0; i < arraysCombined.length; i++) {
		    if(i == arraysCombined.length - 1) {
		        System.out.print(arraysCombined[i]); 
		    } else {
		        System.out.print(arraysCombined[i] + ", ");
		    }
		}
		
		System.out.println("\n\nThe working doc is located at: " + file.getAbsolutePath());

		
	}
	
	
	// This method takes 5 parameters. 3 arrays, and 2 sizes. It sorts and combines the 
	// 2 original arrays into 1 using a counter method to control what indexes are added at which time.
	// whenever an array runs out of elements the method stops comparing arrays and fills in the remainder
	// of the array with leftover values.
	public static void combineAndSortArrays(int[] array1, int[] array2, int[] arraysCombined, int size1, int size2) {
		
		//index counters to control comparing and loading arrays
		int indexCounter1 = 0;
		int indexCounter2 = 0;
		int indexCounter3 = 0;
		
		//this while loop only adds elements to the combined array if both arrays still have additional values
		while(indexCounter1 < size1 && indexCounter2 < size2  ) {
			
			//add array1 index if less than array2 index
			if(array1[indexCounter1] < array2[indexCounter2]) {
				arraysCombined[indexCounter3] = array1[indexCounter1];
				indexCounter1++;
				indexCounter3++;
			}
			
			//add array1 and array2 indices if equal
			else if(array1[indexCounter1] == array2[indexCounter2]) {
				arraysCombined[indexCounter3] = array1[indexCounter1];
				indexCounter1++;
				indexCounter3++;
				arraysCombined[indexCounter3] = array2[indexCounter2];
				indexCounter2++;
				indexCounter3++;
			}
			
			//add array2 index if less than array1 index
			else {
				arraysCombined[indexCounter3] = array2[indexCounter2];
				indexCounter2++;
				indexCounter3++;
			}
		}
		
		//add the remainder of array1 if array 2 ran out first
		for(int i = indexCounter1; i < size1 ; i++ ) {
			arraysCombined[indexCounter3] = array1[i];
			indexCounter3++;
		}
		
		//add the remainder of array2 if array1 ran out of elements first
		for(int i = indexCounter2; i < size2 ; i++ ) {
			arraysCombined[indexCounter3] = array2[i];
			indexCounter3++;
		}
	}
}
