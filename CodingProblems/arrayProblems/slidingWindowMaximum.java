package arrayProblems;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
http://articles.leetcode.com/2011/01/sliding-window-maximum.html
Challenge is to solve the problem in O(n)
 */

public class slidingWindowMaximum {
    private static int[] getSlidingWindowMaximum(int[] inputArray, int window)
    {
        if(inputArray == null || inputArray.length == 0)
            return null;
        if(window < 0)
            throw new RuntimeException("Window value can not be less than 1");

        int inputLength = inputArray.length;

        if(window > inputLength)
            window = inputLength;

        int[] outputArray = new int[inputLength - window + 1];
        Deque<Integer> maximumIndexQ = new LinkedList<Integer>();

        for(int i=0; i< window; i++)
        {
            int currentElem = inputArray[i];
            while(!maximumIndexQ.isEmpty()
                    && inputArray[maximumIndexQ.peekLast()] <= currentElem)
                maximumIndexQ.removeLast();
            maximumIndexQ.addLast(i);
        }

        outputArray[0] = inputArray[maximumIndexQ.peekFirst()];
        for(int i = window; i< inputLength; i++)
        {
            while(!maximumIndexQ.isEmpty()
                    && (i - maximumIndexQ.peekFirst()) >= window)
                maximumIndexQ.removeFirst();

            int currentElem = inputArray[i];
            while(!maximumIndexQ.isEmpty()
                    && inputArray[maximumIndexQ.peekLast()] <= currentElem)
                maximumIndexQ.removeLast();
            maximumIndexQ.addLast(i);

            outputArray[i-window + 1] = inputArray[maximumIndexQ.peekFirst()];
        }

        return outputArray;
    }

    public static void main(String[] args){
        int[] output = getSlidingWindowMaximum(new int[]{23,5,18,2,6,78,4,11,45,65,23,44,1,3,67}, 4);
        System.out.println(Arrays.toString(output));
        int[] output2 = getSlidingWindowMaximum(new int[]{23,5,18,2,6,78,4,11,45,65,23,44,1,3,67}, 5);
        System.out.println(Arrays.toString(output2));
    }
}
