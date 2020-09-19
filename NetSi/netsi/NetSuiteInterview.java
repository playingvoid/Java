package netsuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

/*
 * The implementation of Iterable, providing the "remove duplicate" iteration
 */
class UniqueList<Type> implements Iterable<Type> {
	/*
	 * The iterator implementation, which returns the next unique element
	 */
	private static class UniqueIterator<Type> implements Iterator<Type> {
		// The next next() value to be returned
		private Type nextValue = null;
		// The next() value to be returned
		private Type previousValue = null;
		// Iterator for the original list
		Iterator<Type> inputIterator = null;

		public UniqueIterator(List<Type> inputList) {
			//No null check should be required, as we expect the same exception
			//as of calling the iterator()function when the original list is passed as null
			inputIterator = inputList.iterator();
			// Skips the initial null values
			skipNulls();
			// Initialize the previousValue and the nextValue
			initializeNext();	
		}

		/*
		 * This function is required to initialize the invariance for function
		 * initializeNext().
		 */
		private void skipNulls() {
			while (nextValue == null && inputIterator.hasNext()) {
				nextValue = inputIterator.next();
			}
		}

		/*
		 * This function should only be called when the next value contains a
		 * next valid value to be returned to maintain the invariance
		 */
		private void initializeNext() {
			previousValue = nextValue;
			while (nextValue == null || nextValue.equals(previousValue)) {
				if (inputIterator.hasNext())
					nextValue = inputIterator.next();
				else {
					nextValue = null;
					break;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return previousValue != null;
		}

		@Override
		public Type next() {
			// This check and call to inputIterator.next() is to make sure, when next is getting called
			// it throws the same exception which would have thrown by the iterator on the original list
			if (previousValue == null)
				inputIterator.next();

			Type result = previousValue;
			initializeNext();
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	// The original list which could contain duplicates
	private List<Type> inputList;

	public UniqueList(List<Type> inputList) {
		this.inputList = inputList;
	}

	@Override
	public Iterator<Type> iterator() {
		return new UniqueIterator<Type>(inputList);
	}
}

public class NetSuiteInterview {
	
	// double question2(double a, double epsilon)
	private static double squareRoot(double a, double epsilon) {
		double originalNumber = a; // a in problem statement
		double estimate = originalNumber / 2.0; // x in problem statement

		while (Math.abs(estimate * estimate - a) >= epsilon) {
			estimate = (estimate + originalNumber) / 2.0;
			// System.out.println(estimate);
			originalNumber = a / estimate;
		}
		return estimate;
	}

	// Errored approach for estimate calculation
	@SuppressWarnings("unused")
	private static double squareRootError(double a, double epsilon) {
		double originalNumber = a; // a in problem statement
		double estimate = originalNumber / 2.0; // x in problem statement

		while (Math.abs(estimate * estimate - a) >= epsilon) {
			// System.out.println(estimate);
			estimate = a / estimate;
		}
		return estimate;
	}

	private static void testQuestion2() {
		System.out.println("\nTesting Question 2");
		System.out.println(squareRoot(2.0, 0.001));
		System.out.println(squareRoot(2.0, 1e-6));
		System.out.println(squareRoot(9, 1e-6));
		System.out.println(squareRoot(15, 1e-6));
		System.out.println(squareRoot(25, 1e-6));
		System.out.println(squareRoot(0, 1e-6));
		//Following call, if uncommented, will go in infinite loop
		//squareRootError(2.0, 0.0001);
	}
	
	/*
	 * Function, solution of Question 1 in assignment.
	 */
	// question1(final int[] values)
	private static int[] findUnique1(final int[] values) {
		// Return null if input is a null value
		if (values == null)
			return null;
		// Return an empty array if values has no elements
		if (values.length == 0)
			return new int[] {};
		
		//Count the number of distinct elements in the array, this is required
		//to initialize of result (int[]) array size
		int numberOfDistinctElements = 1;
		int prevValue = values[0];
		for (int i = 1; i < values.length; i++) {
			int currValue = values[i];
			if (currValue != prevValue)
				numberOfDistinctElements++;
			prevValue = currValue;
		}

		// We have to do some redundant logic the counting of distinct element above, 
		// as we want to calculate the size of the result array first, and then we are
		// populating the result array
		int[] result = new int[numberOfDistinctElements];
		result[0] = values[0];
		//The index where the next element will go in the result array
		int currIndex = 1;
		for (int i = 1; i < values.length; i++) {
			//currValue = values[i]
			//prevValue = result[currIndex - 1]
			if (values[i] != result[currIndex - 1]) {
				result[currIndex++] = values[i];
			}
		}

		return result;
	}

	/*
	 * Function, Solution variance of Question 1 which uses the List<Integer>
	 * as input
	 */
	private static List<Integer> findUnique2(final List<Integer> values) {
		if (values == null)
			return null;
		List<Integer> result = new ArrayList<>();
		Integer previous = null;
		for (Integer value : values) {
			if (value != null && !value.equals(previous)) {
				result.add(value);
				previous = value;
			}
		}
		return result;
	}

	private static void testQuestion1FindUnique1(int[] inputArray){
		int[] output = findUnique1(inputArray);
		System.out.println(Arrays.toString(output));
	}
	
	private static void testQuestion1FindUnique2(List<Integer> inputArray){
		System.out.println(findUnique2(inputArray));
	}
	
	private static void testQuestion1FindUnique1() {
		System.out.println("\nTesting QUESTION 1 - Input Array");
		testQuestion1FindUnique1(new int[]{2,2,3,3,3,3,4,5,6,6,6,7});
		testQuestion1FindUnique1(new int[]{});
		testQuestion1FindUnique1(new int[]{1,2,3});
		testQuestion1FindUnique1(new int[]{1,1,1});
		testQuestion1FindUnique1(new int[]{1});
		testQuestion1FindUnique1(null);
	}
	
	private static void testQuestion1FindUnique2() {
		System.out.println("\nTesting QUESTION 1 - List<Integer>");
		testQuestion1FindUnique2(Arrays.asList(2,2,3,3,3,3,4,5,6,6,6,7));
		testQuestion1FindUnique2(Arrays.asList());
		testQuestion1FindUnique2(Arrays.asList(1,2,3));
		testQuestion1FindUnique2(Arrays.asList(1,1,1));
		testQuestion1FindUnique2(Arrays.asList(1));
		testQuestion1FindUnique2(null);
		testQuestion1FindUnique2(Arrays.asList(null, null, 1, 1, null, null,1,1,2,2,null, null));
		testQuestion1FindUnique2(Arrays.asList(null, null, null, null, null, null));
		testQuestion1FindUnique2(Arrays.asList(new Integer[]{null}));
	}
	
	private static void testQuestion1IteratorCase(List<Integer> values) {
		UniqueList<Integer> unqList = new UniqueList<>(values);
		Iterator<Integer> unqItr = unqList.iterator();
		while (unqItr.hasNext()) {
			System.out.print(unqItr.next() + ", ");
		}
		System.out.println();
	}

	private static void testQuestion1Iterator() {
		System.out.println("\nTesting QUESTION 1 - Iterator Implementation");
		testQuestion1IteratorCase(Arrays.asList(null, null, 1, 1, null, null, 1, 1, 2, 2, null, null, 3, 3, null));
		testQuestion1IteratorCase(Arrays.asList(1, 1, 2, 2, null, null, 3, 3, null, 3, 3));
		testQuestion1IteratorCase(Arrays.asList(null, null));
		testQuestion1IteratorCase(Arrays.asList(1, 1, 1));
		testQuestion1IteratorCase(Arrays.asList());
		testQuestion1IteratorCase(Arrays.asList(1, 1, 1, null, null));
		testQuestion1IteratorCase(Arrays.asList(1, 2, 3));
		try{
			testQuestion1IteratorCase(null);
		} catch(Exception e){
			e.printStackTrace();
		}
		testQuestion1IteratorCase(Arrays.asList(null, null, 1, 1, null, null, 1, 1, null, null));
	}
	
	private static void testQuestion1(){
		System.out.println("\nTesting QUESTION 1");
		testQuestion1FindUnique1();
		testQuestion1FindUnique2();
		testQuestion1Iterator();
	}
	
	public static void main(String[] args) {
		testQuestion1();
		testQuestion2();
	}
}
