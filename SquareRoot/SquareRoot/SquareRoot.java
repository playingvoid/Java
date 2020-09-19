package SquareRoot;
import java.util.*;

public class SquareRoot {
	
	static double calculateSquareRoot(double number){
		
		double left, right;
		if(number < 1.0){
			left = number;
			right = 1.0;
		}else{
			right = number;
			left = 1.0;
		}
		
		while(compare(left, right) ==  Ordering.SMALLER){
			double mid = left + 0.5 * (right - left);
			double midSquare = mid * mid;
			Ordering ord = compare(midSquare, number);
			if( ord == Ordering.EQUAL)
				return midSquare;
			else if(ord== Ordering.LARGER){
				right = mid;
			}else{
				left = mid;
			}
		}
		return left;
	}
	
	private static enum Ordering {SMALLER, EQUAL, LARGER}
	
	private static Ordering compare(double left, double right){
		double elipson = 0.00001;
		double diff = (left - right) / right;
		if(diff < -elipson)
			return Ordering.SMALLER;
		else if(diff > elipson)
			return Ordering.LARGER;
		return Ordering.EQUAL;
			
	}
	
	public static int minimumPathToTotal(List<List<Integer>> triangle) {
		if(triangle.isEmpty())
			return 0;
		
		List<Integer> prevRow = new ArrayList<Integer>(triangle.get(0));
		
		for(int i = 1; i < triangle.size(); i++){
			List<Integer> currRow = new ArrayList<>(triangle.get(i));
			
			currRow.set(0, currRow.get(0) + prevRow.get(0));
			
			for(int j = 1; j< currRow.size() - 1; j++){
				int prevRowJ = prevRow.get(j);
				int prevRowJM1 = prevRow.get(j-1);
				currRow.set(j, currRow.get(j) + Math.min(prevRowJM1, prevRowJ));
			}
			currRow.set(currRow.size()-1, currRow.get(currRow.size() - 1) + prevRow.get(prevRow.size()-1));
			prevRow = currRow;
		}
		
		return Collections.min(prevRow);
	}
	
	public static void main(String[] args){
		List<List<Integer>> triangle = new ArrayList<>();
		
		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2));
		triangle.add(a);
		triangle.add(new ArrayList<>(Arrays.asList(10,1)));
		triangle.add(new ArrayList<>(Arrays.asList(1,100, 200)));
		
		System.out.println(minimumPathToTotal(triangle));
		
		//System.out.println(calculateSquareRoot(0.25));
	}
}
