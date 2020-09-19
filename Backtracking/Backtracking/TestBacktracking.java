package Backtracking;

import java.util.ArrayList;

public class TestBacktracking {
	
	private static void testSubsetSum(){
		ArrayList<ArrayList<Integer>> output = SubsetSum.findSubsetSum(new int[]{3, 2, 5, 1, 9, 8, 11, 4}, 10);
		for(ArrayList<Integer> set : output){
			System.out.println(set);
		}
	}
	
	public static void main(String[] args){
		testSubsetSum();
		System.out.println("Passed");
	}

}
