package stringProblems.minimumWindowSubstring;

import java.util.*;

public class MinimumWindowSubstring {
	
	public static String findMimimumWindowSubstring(String input, String sample){
		
		String output = null;
		HashMap<Character, Integer> needed = getNeededMap(sample);
		HashMap<Character, Integer> found = new HashMap<Character, Integer>();
		int currentCount = 0;
		
		int start = 0, end = 0;
		while(currentCount < sample.length() && end < input.length()){
			char currChar = input.charAt(end);
			if(needed.containsKey(currChar)){
				if(!found.containsKey(currChar)){
					found.put(currChar, 0);
				}
				
				if(found.get(currChar) < needed.get(currChar)){
					currentCount ++;
				}
				
				found.put(currChar, found.get(currChar) + 1);
			}
			end++;
		}
		
		if(currentCount != sample.length())
			return null;
		
		end --;
		int globalStart = 0, globalEnd = end, minLength = Integer.MAX_VALUE;
		while(end < input.length() && start <= end){
			
			if(currentCount == sample.length()){
				if(end - start + 1 < minLength){		
					globalStart  = start;
					globalEnd = end;
					minLength = end - start + 1;
				}
				
				char currChar = input.charAt(start);
				if(needed.containsKey(currChar)){
					found.put(currChar, found.get(currChar) - 1);
					
					if(found.get(currChar) < needed.get(currChar)){
						currentCount --;
					}
				}
				start++;
			}
			else {
				end++;
				if(end >= input.length())
					break;
				char currChar = input.charAt(end);
				if(needed.containsKey(currChar)){
					if(found.get(currChar) < needed.get(currChar)){
						currentCount ++;
					}
					found.put(currChar, found.get(currChar) + 1);
				}
			}
		}
		
		output = input.substring(globalStart, globalEnd + 1);
		return output;
		
	}
	
	private static HashMap<Character, Integer> getNeededMap(String sample) {
		HashMap<Character, Integer> neededMap = new HashMap<Character, Integer>();
		for(char c : sample.toCharArray()){
			if(!neededMap.containsKey(c))
				neededMap.put(c, 0);
			neededMap.put(c, neededMap.get(c) + 1);
		}
		return neededMap;
	}

	public static void main(String[] args){
		System.out.println(findMimimumWindowSubstring("this is a test string", "tist"));
		System.out.println(findMimimumWindowSubstring("ADOBECODEBANC", "ABC"));
		System.out.println(findMimimumWindowSubstring("acbbaca", "aba"));
	}

}
