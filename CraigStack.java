/* Craig S
 * Description:
 * 
 * This program demonstrates stack operations using both the Java Collections Framework (JCF)
 * and a custom generic stack class. It counts even integers in a JCF stack without modifying it,
 * and performs generic stack manipulation including reading from files, combining stacks,
 * sorting stacks, and removing duplicates. All operations are implemented for both Integer
 * and String types using generic methods and a custom GenericStack<E> class. The GenericStack<E> 
 * class is included in this java file to simplify copying to an IDE. Several files are required for 
 * this program: Numbers1.txt, Numbers2.txt, Strings1.txt, and Strings2.txt.
 */

import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CraigStack {
	public static void main(String args[]) throws FileNotFoundException{
		
		
		//Part one: 
		
		//initialize int array
		int[] values = {12, 9, 3, 10, 4, 2, 5, 15, 7, 11, 14};
		
		//create a stack 
		Stack<Integer> stack1 = new Stack<>();
		
		//fill stack with array
		for(int i = 0; i < values.length; i++) {
			
			stack1.push(values[i]);
		}
		//call findNumEven
		int numberOfEvenVals = findNumEven(stack1);
		
		System.out.printf("There are %s even numbers in the stack\n", numberOfEvenVals);
		
		System.out.println("\nStack after finding the number of even values: ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(stack1);
		
		//Part Two: 
		
		//two generic stacks to hold integer objects
		GenericStack<Integer> numStack1 = new GenericStack<>();
		
		GenericStack<Integer> numStack2 = new GenericStack<>();
		
		//fill stacks from numbers1.txt and numbers2.txt
		
		//file and scanner object for Numbers1.txt
		
		File numFile1 = new File("Numbers1.txt");
		Scanner inputFile = new Scanner(numFile1);
		
		while(inputFile.hasNext()){
			Integer number = inputFile.nextInt();
			numStack1.push(number);
		}
		
		inputFile.close();
		
		//file and scanner object for Numbers2.txt
		File numFile2 = new File("Numbers2.txt");
		inputFile = new Scanner(numFile2);
		
		//load numStack2 with the numbers from Numbers2.txt
		while(inputFile.hasNext()){
			Integer number = inputFile.nextInt();
			numStack2.push(number);
		}
		inputFile.close();
		
		//print each stack (generic method)
		System.out.println("\nValues read from Numbers1.txt and pushed onto stack1");
		System.out.println("--------------------------------------------------------------- ");
		printStack(numStack1);
		
		System.out.println("\nValues read from Numbers2.txt and pushed onto stack2");
		System.out.println("--------------------------------------------------------------- ");
		printStack(numStack2);
		
		//combine the 2 stacks (generic method)
		GenericStack<Integer> combinedStack = combineStacks(numStack1,numStack2);
		
		//sort stack (generic sortStack method)
		
		//new stack to hold sorted values
		GenericStack<Integer> sortedStack = new GenericStack<>();
		
		//method that sorts stack
		sortStack(combinedStack, sortedStack);
		
		//display sorted stack contents
		System.out.println("\nCombined Number Stack With Duplicates - lowest value on top: ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(sortedStack);
				
		//remove duplicates from the sorted stack
		GenericStack<Integer> duplicatesStackInt = removeDuplicates(sortedStack);
		
		//print stacks
		System.out.println("\nCombined Number Stack Duplicates Removed - lowest value on top:  ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(sortedStack);
		
		System.out.println("\nDuplicates Number Stack - largest value on top:  ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(duplicatesStackInt);
		
		//Part 2 repeat with strings:
		
		//read and load string files
				
		//two generic stacks to hold string objects
		GenericStack<String> stringStack1 = new GenericStack<>();
		
		GenericStack<String> stringStack2 = new GenericStack<>();
		
		//fill stacks from strings1.txt and strings2.txt
		
		//file and scanner object for strings1.txt
		
		File stringFile1 = new File("Strings1.txt");
		inputFile = new Scanner(stringFile1);
		
		//load stringStack1 with strings from strings1.txt
		while(inputFile.hasNext()){
			String string = inputFile.next();
			stringStack1.push(string);
		}
		
		inputFile.close();
		
		//file and scanner object for strings2.txt
		File stringFile2 = new File("Strings2.txt");
		inputFile = new Scanner(stringFile2);
		
		//load stringStack2 with strings from strings2.txt
		while(inputFile.hasNext()){
			String string = inputFile.next();
			stringStack2.push(string);
		}
		inputFile.close();
		
		//print each stack (generic method)
		System.out.println("\nValues read from strings1.txt and pushed onto stack1");
		System.out.println("--------------------------------------------------------------- ");
		printStack(stringStack1);
		
		System.out.println("\nValues read from strings2.txt and pushed onto stack2");
		System.out.println("--------------------------------------------------------------- ");
		printStack(stringStack2);
		
		//combine the 2 stacks (generic method)
		GenericStack<String> combinedStringStack = combineStacks(stringStack1,stringStack2);
		
		//sort stack (generic sortStack method)
		
		//new stack to hold sorted values
		GenericStack<String> sortedStringStack = new GenericStack<>();
		
		//method that sorts stack
		sortStack(combinedStringStack, sortedStringStack);
		
		//display sorted stack contents
		System.out.println("\nCombined String Stack With Duplicates - lowest value on top:  ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(sortedStringStack);
				
		//remove duplicates from the sorted stack
		GenericStack<String> duplicatesStringStack = removeDuplicates(sortedStringStack);
		
		//print stacks
		System.out.println("\nCombined String Stack Duplicated Removed - lowest value on top:   ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(sortedStringStack);
		
		System.out.println("\nDuplicates String Stack - largest value on top:   ");
		System.out.println("--------------------------------------------------------------- ");
		printStack(duplicatesStringStack);
		
		
	}//main
	
	//===================================================================Methods================================================================//
	
	//this method counts and returns the number of even numbers in a stack of type Integer
	public static int findNumEven(Stack<Integer> inputStack) {
		
		//temp stack for holding values:
		Stack<Integer> tempStack = new Stack<>();
		
		//variables
		int stackSize = inputStack.size();
		int numOfEvenVals = 0;
		
		//find even numbers 
		for(int i = 0; i < stackSize; i++) {
			
			//remove top value from inputStack
			int topValue = inputStack.pop();
			//save value in temp stack
			tempStack.push(topValue);
			//evaluate if even and increment counter
			if(topValue % 2 == 0) {
				numOfEvenVals++;
			}
			
		}
		
		//return values to original stack
		for(int i = 0; i < stackSize; i++) {
			
			int tempTopValue = tempStack.pop();
			inputStack.push(tempTopValue);
		}
		
		//return count
		return numOfEvenVals;
	}//findNumEven()
	
	//This method prints an integer stack by popping off one stack and pushing to a temp stack and then returning values back to original stack
	public static void printStack(Stack<Integer> inputStack) {
		
		Stack<Integer>  tempStack = new Stack<>();
		int stackSize = inputStack.size();
		
		//print stack
		
		for(int i = 0; i < stackSize; i++) {
			
			int topValue = inputStack.pop();
			tempStack.push(topValue);
			
			System.out.println(topValue);
	
		}
		System.out.println();
		
		
		//return values from temp stack back to original stack
		for(int i = 0; i < stackSize; i++) {
			
			int topValue = tempStack.pop();
			 inputStack.push(topValue);
		
		}
	}//printStack()
	
	//generic stack print method
	public static <E> void printStack(GenericStack<E> stack) {
		
		Stack<E>  tempStack = new Stack<>();
		int stackSize = stack.getSize();
		
		//print stack
	
		for(int i = 0; i < stackSize; i++) {
			
			E topValue = stack.pop();
			tempStack.push(topValue);
			
			System.out.println(topValue);
			
		}
		
		System.out.println();
		
		//return values from temp stack back to original stack
		for(int i = 0; i < stackSize; i++) {
			
			E topValue = tempStack.pop();
			 stack.push(topValue);
		
		}
	}// generic printStack()
	
	//generic combine stack method
	public static <E> GenericStack<E> combineStacks(GenericStack<E> stack1, GenericStack<E> stack2){
		
		GenericStack<E> combinedStack = new GenericStack<>();
		GenericStack<E> tempStack = new GenericStack<>();
		
		//add stack1 to combinedStack
		int size = stack1.getSize();
		
		for(int i = 0; i < size; i++) {
			
			E tempObj = stack1.pop();
			tempStack.push(tempObj);
			combinedStack.push(tempObj);
		}
		
		//return Stack1 to original configuration
		for(int i = 0; i < size; i++) {
			
			E tempObj = tempStack.pop();
			stack1.push(tempObj);
		}
		
		//add stack2 to combinedStack
		size = stack2.getSize();
			
		for(int i = 0; i < size; i++) {
			
			E tempObj = stack2.pop();
			tempStack.push(tempObj);
			combinedStack.push(tempObj);
		}
		
		//return Stack2 to original configuration
		for(int i = 0; i < size; i++) {
			
			E tempObj = tempStack.pop();
			stack2.push(tempObj);
		}
		
		return combinedStack;
	}//combineStacks()
	
	//Generic sort Stack Method
	public static <E extends Comparable<E>> void sortStack(GenericStack<E> unsortedStack, GenericStack<E> sortedStack) {
		
		E tempVar;
		
		//while unsorted stack has values continue iterating
		
		while(!unsortedStack.isEmpty()) {
			
			tempVar = unsortedStack.pop();
			//while sorted stack has values and sorted stack top value is bigger than the temp value move values to unsorted stack
			while(!sortedStack.isEmpty() && sortedStack.peek().compareTo(tempVar) < 0) {
				
				//when sorted stack value is bigger than the temp var continue moving sorted stack value back to the unsorted stack
				unsortedStack.push(sortedStack.pop());
				
			}
			
			//move top value of unsorted stack back to the temp var			
			sortedStack.push(tempVar);
		}
	}//sortStack()
	
	//generic method that removes duplicates
	public static <E extends Comparable<E>> GenericStack<E> removeDuplicates(GenericStack<E> stack){
		
		//two new GenericStacks
		GenericStack<E> tempStack = new GenericStack<>();
		GenericStack<E> duplicatesStack = new GenericStack<>();
		
		E tempVar;
		//while original stack is empty iterate
		while(!stack.isEmpty()) {
			
			tempVar = stack.pop();
			
			if(tempStack.isEmpty()) {
				tempStack.push(tempVar);
				
				continue;
			}
			
			//if values are still in stack and they are equal to top of temp stack push to duplicate stack and add new value to tempVar
			while(!stack.isEmpty() && tempVar.compareTo(tempStack.peek()) == 0) {
				
				duplicatesStack.push(tempVar);
				
				tempVar = stack.pop();
				
			}
			
			//check that last tempVar is compared before being added
		    if (tempVar.compareTo(tempStack.peek()) == 0) {
		        duplicatesStack.push(tempVar);
		    } else {
		        tempStack.push(tempVar);
		    }
		}
		
		//return values to original Stack
		
		while(!tempStack.isEmpty()) {
			stack.push(tempStack.pop());
		}
		
		return duplicatesStack;
		
	}

}//test class

//=================================================================================================Classes======================================================================================================//

//Generic Stack class using an ArrayList and Stack methods
class GenericStack<E> {
	
	//generic ArrayList
	private ArrayList<E> list;
	
	//constructor
	public GenericStack() {
		this.list = new ArrayList<>();
	}
	
	//returns true if list size is zero
	public boolean isEmpty() {
		if(list.size() == 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//returns the size of the list
	public int getSize() {
		return list.size();
	}
	
	//returns the object on top of the stack
	public E peek() {
		
		return list.get(list.size()-1);	
	}
	
	//return and remove the object on top of the stack
	public E pop() {
		
		int size = list.size();
		E obj = list.get(size-1);
		list.remove(size-1);
		return obj;
	}
	
	//adds a value to the top of the stack
	public void push(E object) {
		
		list.add(object);
	}

}//GenericStack class
