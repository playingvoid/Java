package earnUp;

import java.util.*;
import java.security.InvalidParameterException;

public class Problem3 {
	// Space efficient when n is very large when compared to m
	public static List<Integer> getRandomMLessThanN(int m, int n){
		if(m > n || m < 1 )
			throw new InvalidParameterException("Invalid input parameters");
		Map<Integer, Integer> randomNs = new HashMap<Integer, Integer>();
		Random randomGen = new Random();
		for(int index = 0; index < m ; index++){
			// Get a random index greater than the current "index" being considered
			int randomIndex = index + randomGen.nextInt(n - index);			
			// If we have already touched the value at this random index location then 
			// consider that value, else consider the value as random index itself
			int valueAtRandomIndex =  randomNs.getOrDefault(randomIndex, randomIndex);
			
			// Now put(treat) the earlier found random value as the value found for current index
			
			// If there already a value at this index, consider it for replacement, else, just replace
			// this index itself
			int valueAtIndex = randomNs.getOrDefault(index, index);
			
			randomNs.put(index, valueAtRandomIndex);
			randomNs.put(randomIndex, valueAtIndex);
		}
		//System.out.println(randomNs);
		List<Integer> result = new ArrayList<>();
		for(int index = 0 ; index < m; index++){
			result.add(randomNs.get(index));
		}
	
		return result;
	}
	public static void main(String[] args){
		System.out.println(getRandomMLessThanN(3, 20));
		System.out.println(getRandomMLessThanN(20, 20));
	}
}
