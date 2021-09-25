package recursionProblems;

import java.util.*;

public class Permutation2 {
    public static void main(String[] args){
        List<List<Integer>> output = permute(new int[]{1,2,1,3});
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
        HashSet<Integer> encountered = new HashSet<>();
        for(int i = startIndex; i < slate.size(); i++){
            if(encountered.contains(slate.get(i))){
                continue;
            }
            encountered.add(slate.get(i));
            Collections.swap(slate, startIndex, i);
            permutationHelper(startIndex+1, slate, output);
            Collections.swap(slate, startIndex, i);
        }
    }
}
