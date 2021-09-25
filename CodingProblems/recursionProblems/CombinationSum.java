package recursionProblems;

import java.util.*;

// Another way, check solution for https://leetcode.com/problems/combination-sum/
// related problem: DP to count, https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/
public class CombinationSum {

    public static void main(String[] args){
        List<List<Integer>> answer1 = combinationSum(new int[] {2,3,6,7}, 7);
        System.out.println(answer1);
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0)
            return new ArrayList<>();

        List<List<Integer>> output = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();
        combinationSumHelper(candidates, 0, target, output, currentSequence);
        return output;
    }

    public static void combinationSumHelper(int[] candidates,
                                     int startIndex,
                                     int remainingSum,
                                     List<List<Integer>> output,
                                     List<Integer> currentSequence)
    {
        if(remainingSum == 0)
        {
            output.add(new ArrayList<>(currentSequence));
            return;
        }

        if(remainingSum < 0 || startIndex == candidates.length)
            return;
        currentSequence.add(candidates[startIndex]);
        combinationSumHelper(
                candidates,
                startIndex,
                remainingSum - candidates[startIndex],
                output,
                currentSequence);
        currentSequence.remove(currentSequence.size()-1);
        combinationSumHelper(
                candidates,
                startIndex + 1,
                remainingSum,
                output,
                currentSequence);

    }

}
