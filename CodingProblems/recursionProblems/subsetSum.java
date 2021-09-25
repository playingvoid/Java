/*
Given a set of a numbers and a value sum, check if a non empty sub set exist which adds to the given sum
 */
package recursionProblems;

public class subsetSum {
    public static boolean isSum(int[] numbers, int sum, int startIndex, boolean numberConsidered){
        if(numberConsidered && sum == 0)
            return true;
        for(int index = startIndex; index < numbers.length; index++){
            if(isSum(numbers, sum - numbers[index], index + 1, true))
                return true;
        }
        return false;
    }

    public static boolean isSum(int[] numbers, int sum){
        return isSum(numbers, sum, 0, false);
    }
    public static void main(String[] args){
        System.out.println(isSum(new int[]{1,4,5}, 11));
        System.out.println(isSum(new int[]{1,4,5}, 5));
        System.out.println(isSum(new int[]{1,4,5}, 0));
        System.out.println(isSum(new int[]{1,4,-1}, 0));
    }
}
