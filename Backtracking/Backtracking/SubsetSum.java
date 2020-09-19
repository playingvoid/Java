package Backtracking;
import java.util.ArrayList;

public class SubsetSum {
	
	public static ArrayList<ArrayList<Integer>> findSubsetSum(int[] inputSet, int givenSum){
		if(inputSet == null) return null;
		
		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<Integer>();
		populateSubsets(inputSet, givenSum, 0, current,output);
		return output;
	}
	
	
	private static void populateSubsets(int[] inputSet, int givenSum, 
					int index, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> output){
		
		if(givenSum == 0){
			ArrayList<Integer> cln =new ArrayList<Integer>(current);
			output.add(cln);
			return;
		}
		
		for(int i=index; i<inputSet.length; i++){
			if(inputSet[i] <= givenSum){
				current.add(inputSet[i]);
				populateSubsets(inputSet, givenSum - inputSet[i], i + 1, current, output);
				current.remove(current.size() - 1);
			}
		}
	}
	

}
