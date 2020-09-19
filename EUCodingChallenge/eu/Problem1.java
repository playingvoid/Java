package earnUp;

import java.util.Arrays;

public class Problem1 {
	//For problem 1, assumption is - "data" is of type int
	public static int[] reverse(final int[] input){
		if(null == input)
			return null;
		
		int[] output = new int[input.length];
		for(int i = 0; i < input.length; i++)
			output[i] = input[input.length - 1 - i];
		return output;
	}
	
	public static void main(String[] args){
		System.out.println(reverse(null));
		System.out.println(Arrays.toString(reverse(new int[]{})));
		System.out.println(Arrays.toString(reverse(new int[]{1})));
		System.out.println(Arrays.toString(reverse(new int[]{1,2,3,4,5})));
		System.out.println(Arrays.toString(reverse(new int[]{1,2,3,4,5,6})));
	}
}
