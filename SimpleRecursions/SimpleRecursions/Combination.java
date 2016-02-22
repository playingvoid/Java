package SimpleRecursions;

//print power set of a given input string
public class Combination {
	public static void printCombinations(String input){
		printCombinations(input, 0, new StringBuilder());
	}
	
	private static void printCombinations(String input, int start, StringBuilder tillNow){
		if(input == null){
			printCombination(input);
			return;
		}
		
		for(int i = start; i < input.length(); i++){
			tillNow.append(input.charAt(i));
			printCombination(tillNow.toString());
			printCombinations(input, i + 1, tillNow);
			tillNow.deleteCharAt(tillNow.length() - 1);
		}
	}
	
	private static void printCombination(String input){
		if(input == null)
			System.out.println("{}");
		else
			System.out.println("{"+input+"}");
	}
}
