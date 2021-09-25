package recursionProblems;

import java.util.*;

public class Combination {

    public static void main(String[] args){
        List<List<Integer>> output = ComputeCombination(Arrays.asList(1,2,3));
        System.out.println(output);
    }

    static List<List<Integer>> ComputeCombination(List<Integer> input){
        if(input == null)
            return null;

        List<List<Integer>> output = new ArrayList<>();
        List<Integer> slate = new ArrayList<>();
        ComputeCombinationHelper(0, input, slate, output);
        return output;
    }

    static void ComputeCombinationHelper(int startIndex, List<Integer> input, List<Integer> slate, List<List<Integer>> output){
        if(startIndex == input.size()){
            return;
        }

        for(int index = startIndex; index < input.size(); index++){
            slate.add(input.get(index));
            output.add(new ArrayList<>(slate));
            ComputeCombinationHelper(index + 1, input, slate, output);
            slate.remove(slate.size()-1);
        }
    }
}
