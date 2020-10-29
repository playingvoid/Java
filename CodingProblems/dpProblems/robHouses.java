package dpProblems;

/*
PROBLEM:
Given an array of values in a house, where value of index i represents the value when House i gets robbed.
Find the maximum value one thief can make by robbing houses, given s/he can not rob adjacent houses.

Dynamic Programming

Maximum value which can be make by robbing house i
MaxRobbedValue[i] = Max(MaxRobbedValue(i-1), value[i] + MaxRobbedValue(i-2))

 */


public class robHouses {
    private static int maxRobbedValue(int[] values){
        int[] maxValues = new int[values.length + 1];
        maxValues[0] = 0;
        maxValues[1] = values[0];
        for(int valueIndex = 1; valueIndex <  values.length; valueIndex++){
            maxValues[valueIndex + 1] = Math.max(maxValues[valueIndex], values[valueIndex] + maxValues[valueIndex - 1]);
        }
        return maxValues[maxValues.length - 1];
    }

    private static int maxRobbedValueOptimzed(int[] values){
        int prevMax = 0;
        int currMax = values[0];
        for(int valueIndex = 1; valueIndex <  values.length; valueIndex++){
            int saveCurrMax = currMax;
            currMax = Math.max(prevMax + values[valueIndex], currMax);
            prevMax = saveCurrMax;
        }
        return currMax;
    }

    public static void main(String[] args){
        System.out.println(maxRobbedValue(new int[]{3, 6}));
        System.out.println(maxRobbedValue(new int[]{6, 3}));
        System.out.println(maxRobbedValue(new int[]{6, 10, 2, 1, 5}));

        System.out.println(maxRobbedValueOptimzed(new int[]{3, 6}));
        System.out.println(maxRobbedValueOptimzed(new int[]{6, 3}));
        System.out.println(maxRobbedValueOptimzed(new int[]{6, 10, 2, 1, 5}));
    }
}
