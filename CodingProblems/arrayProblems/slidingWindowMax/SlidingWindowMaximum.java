package arrayProblems.slidingWindowMax;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
	
	/*
	 * Calculates the sliding window maximum
	 * 
	 * INPUT
	 * @input: integer array agains which sliding window maximum should be performed
	 * int window: size of the sliding window
	 * RETURN 
	 * @return: An integer array containing sliding window maximum values. The @return
	 * array contains:
	 * 0th index -> the maximum value of 0th to (@window-1)th index in @input
	 * 1st index -> the maximum value of 1st to (@window)th index in @input 
	 * 2nd index -> the maximum value of 2nd to (@window+1)th index in @input
	 * and so forth
	 */
	public static int[] getMaximums(int[] input, int window) {
		if (input == null || input.length == 0 || window <= 0)
			throw new RuntimeException("Invalid input parameter");

		Deque<Integer> maxHolder = new ArrayDeque<Integer>();
		
		//If window size is more than the input size then change the window size
		window = input.length < window ? input.length : window;

		int[] output = new int[input.length - window + 1];

		for (int index = 0; index < window; index++) {
			while (!maxHolder.isEmpty() && input[maxHolder.peekLast()] <= input[index])
				maxHolder.removeLast();
			maxHolder.addLast(index);
		}

		for (int index = window; index < input.length; index++) {
			output[index - window] = input[maxHolder.peekFirst()];

			// Remove all the index from the back of the queue whose value 
			// is lesser than the current element value
			while (!maxHolder.isEmpty() && input[maxHolder.peekLast()] <= input[index])
				maxHolder.removeLast();
			// Remove all the index from the front of the queue which are
			// falling outside the window
			while (!maxHolder.isEmpty() && maxHolder.peekFirst() <= index - window)
				maxHolder.removeFirst();
			
			//Last value in the queue gives the answer
			maxHolder.addLast(index);
		}

		output[input.length - window] = input[maxHolder.peekFirst()];

		return output;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
		int[] output = getMaximums(input, 3);
		for (int i : output)
			System.out.print(i + "\t");
		
		System.out.println();
		input = new int[] { 1, 3, -1, -3, 5 };
		output = getMaximums(input, 10);
		for (int i : output)
			System.out.print(i + "\t");
		
		input = new int[] {1,5,3,1,4,5,3,5,6,2};
		output = getMaximums(input, 3);
		for (int i : output)
			System.out.print(i + "\t");
	}
}
