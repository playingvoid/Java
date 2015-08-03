package SWM;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum 
{
	public static int[] getMaximums(int[] input, int window)
	{
		if(input == null || input.length == 0 || window <= 0)
			throw new RuntimeException("Invalid input parameter");
		
		Deque<Integer> maxHolder = new ArrayDeque<Integer>();
		
		int maxWindow = input.length < window ? input.length : window;
		
		int[] output = new int[input.length - maxWindow + 1];
		
		for(int index = 0; index < maxWindow; index++)
		{
			while(!maxHolder.isEmpty() && input[maxHolder.peekLast()] <= input[index])
				maxHolder.removeLast();
			maxHolder.addLast(index);
		}
		
		for(int index = maxWindow; index < input.length; index++)
		{
			output[index - maxWindow] = input[maxHolder.peekFirst()];
			
			//Remove all the index from the queue back which values are lesser than the
			//current element value
			while(!maxHolder.isEmpty() && input[maxHolder.peekLast()] <= input[index])
				maxHolder.removeLast();
			//Remove all the index from the front of the queue which are falling outside the window
			while(!maxHolder.isEmpty() && maxHolder.peekFirst() <= index - maxWindow)
				maxHolder.removeFirst();
			
			maxHolder.addLast(index);
		}
		
		output[input.length - maxWindow] = input[maxHolder.peekFirst()];
				
		return output;
	}
	
	public static void main(String[] args) 
	{
		int[] input = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
		int[] output = getMaximums(input, 3);
		for(int i : output)
			System.out.print(i + "\t");
		System.out.println();
		input = new int[]{1, 3, -1, -3, 5};
		output = getMaximums(input, 10);
		for(int i : output)
			System.out.print(i + "\t");
	}

}
