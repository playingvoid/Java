package LongestMonotonic;

public class MonotonicArray 
{
	private static class Result{
		public int startIndex;
		public int endIndex;
		public int length;
		
		@Override
		public String toString(){
			return "start index = " + startIndex + "\t end index = " + endIndex + "\t length = " + length; 
		}
	}
	private static Result getLongestMonotonic(int[] input){
		Result globalResult = new Result();
		Result currResult = new Result();
		
		globalResult.startIndex = 0;
		globalResult.endIndex = 0;
		globalResult.length = 0;
		
		currResult.startIndex = 0;
		currResult.endIndex = 0;
		currResult.length = 1;
		
		for(int i = 1; i< input.length; i++){
			if(input[i] >= input[i-1]){
				currResult.endIndex = i;
				currResult.length ++;
			} else {
				currResult.startIndex = i;
				currResult.endIndex = i;
				currResult.length = 1;
			}
			
			if(globalResult.length < currResult.length){
				globalResult.startIndex = currResult.startIndex;
				globalResult.endIndex = currResult.endIndex;
				globalResult.length = currResult.length;
			}			
		}
		
		return globalResult;
	}
	public static void main(String[] args){
		int[] intArr = new int[]{1, 2, 3};
		System.out.println(getLongestMonotonic(intArr));
		intArr = new int[]{-1, 4, 5,6, -10, -1, 3, 4, 5, 6, 7, 1};
		System.out.println(getLongestMonotonic(intArr));
		intArr = new int[]{1};
		System.out.println(getLongestMonotonic(intArr));
	}
}
