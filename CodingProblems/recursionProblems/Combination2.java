package recursionProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Combination2 {
    public static void main(String[] args){
        List<List<Integer>> output = ComputeCombination(Arrays.asList(1,1,3,3));
        System.out.println(output);
    }

    static List<List<Integer>> ComputeCombination(List<Integer> input){
        if(input == null)
            return null;
        Collections.sort(input);
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> slate = new ArrayList<>();
        ComputeCombinationHelper(0, input, slate, output);
        return output;
    }

    static void ComputeCombinationHelper(int startIndex, List<Integer> input, List<Integer> slate, List<List<Integer>> output){
        if(startIndex == input.size()){
            return;
        }
        int index = startIndex;
        while(index < input.size()){
            int lastIndex = getLastIndex(index, input);

            for(int numInput = 1; numInput <= lastIndex-index+1; numInput ++) {
                addToSlate(slate, input.get(index), numInput);
                output.add(new ArrayList<>(slate));
                ComputeCombinationHelper(lastIndex + 1, input, slate, output);
                removeFromSlate(slate, numInput);
            }
            index = lastIndex+1;
        }
    }

    static int getLastIndex(int startIndex, List<Integer> input){
        int lastIndex = startIndex;

        while(lastIndex < input.size() && input.get(lastIndex) == input.get(startIndex))
            lastIndex++;
        return lastIndex-1;
    }

    static void addToSlate(List<Integer> slate, int value, int times){
        for(int i=0; i<times; i++)
            slate.add(value);
    }

    static void removeFromSlate(List<Integer> slate, int times){
        for(int i =0; i<times; i++){
            slate.remove(slate.size()-1);
        }
    }
}
