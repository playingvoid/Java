package earnUp;

import java.util.*;
import java.security.InvalidParameterException;

public class Problem4 {
	//Assumption - Array elements are of Type Integer.
	//Assumption - Function will change the input array
	public static List<Integer> getMRandomElement(int[] array, int m){	
		if(array == null || array.length < m)
			throw new InvalidParameterException("Invalid input parameters");
		Random randomGen = new Random();
		for(int index = 0; index < m ; index++){
			int randomIndex = index + randomGen.nextInt(array.length - index);	
			int temp = array[index];
			array[index] = array[randomIndex];
			array[randomIndex] = temp;
		}
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < m ; i++){
			result.add(array[i]);
		}
		return result;
	}
	public static void main(String[] args){
		System.out.println(getMRandomElement(new int[]{1,2,3,4,5,6,7,8}, 3));
		System.out.println(getMRandomElement(new int[]{1,2,3,4,5,6,7,8}, 8));
	}
}
