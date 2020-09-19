package earnUp;

import java.io.File;
import java.util.*;
public class Problem2 {
	// Assumptions around word definition
	// 1. A word will never be of length larger than Integer.MAX_VALUE
	// 2. A word can be separated by following characters - '.', ',', '?', '!', ';'
	// Any other special characters are part of word
	// 3. A word is case sensitive
	private static final String SPECIAL_CHAR_REGEX = "[\\s+\\.,?!;]+";
	
	public static Map<String, Integer> getWordCount(String fileName){
		Scanner sc = null;
		try{
			File inputFile = new File(fileName);
			//Assumption - file is UTF-8 encoded 
			sc = new Scanner(inputFile, "UTF-8");
			sc.useDelimiter(SPECIAL_CHAR_REGEX);
			Map<String, Integer> result = new HashMap<>();
			while(sc.hasNext()){
				String word = sc.next();
				if(word.equals(""))
					continue;
				result.put(word, result.getOrDefault(word, 0) + 1);
			}
			return result;
		} catch(Exception e){
			throw new RuntimeException(e);
		} finally {
			if(sc != null)
				sc.close();
		}
	}
	
	public static void printWordCount(String fileName){
		Map<String, Integer> wordCounts = getWordCount(fileName);
		for(Map.Entry<String, Integer> entry : wordCounts.entrySet()){
			System.out.println(entry.getValue() + " " + entry.getKey());
		}
	}
	
	public static void main(String[] args){
		if(args.length > 0)
			printWordCount(args[0]);
		else 
			printWordCount("problem2input.txt");
	}
}
