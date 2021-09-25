package recursionProblems;

import java.util.*;

public class Permutation {

    public static void main(String[] args){
        List<List<Integer>> output = permute(new int[]{1,2,3});
        System.out.println(output);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output =  new ArrayList<>();
        List<Integer> slate = new ArrayList<Integer>(nums.length);
        for (int i : nums)
        {
            slate.add(i);
        }
        System.out.println(slate);
        permutationHelper(0, slate, output);
        return output;
    }

    public static void permutationHelper(int startIndex, List<Integer> slate, List<List<Integer>> output){
        System.out.println("startIndex: " + startIndex + " slate" + slate);
        if(startIndex == slate.size()){
            System.out.println("output: "+slate);
            output.add(new ArrayList<Integer>(slate));
            return;
        }

        for(int i = startIndex; i < slate.size(); i++){
            Collections.swap(slate, startIndex, i);
            permutationHelper(startIndex+1, slate, output);
            Collections.swap(slate, startIndex, i);
        }
    }
}
