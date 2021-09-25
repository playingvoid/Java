package recursionProblems;


import java.util.*;

public class RandomPickWeight {

    private int[] cw;
    private int[] w;
    private Random rand;
    private int maxRandomVal;
    public RandomPickWeight(int[] w) {
        this.w = w;
        cw = new int[w.length];
        int sum = 0;
        for(int i = 0; i < this.w.length; i++){
            sum += this.w[i];
            cw[i] = sum;
        }
        maxRandomVal = sum;
        rand = new Random();
        printArray(w);
        printArray(cw);

    }

    public int pickIndex() {
        //System.out.println("pickIndex maxRandomVal: " + maxRandomVal);
        int target = -1;
        if(maxRandomVal > 1)
            target = rand.nextInt(maxRandomVal);

        //System.out.println("pickIndex target 1: " + target);
        return searchFirstGreater(target);
    }

    private int searchFirstGreater(int target){
        //System.out.println("searchFirstGreater: " + target);
        int left = 0, right = cw.length - 1;
        int numLoops = 0;
        while(left <= right){
            numLoops++;
            int mid = left + (right - left ) /2;
            if(cw[mid] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        System.out.println("numLoops: " + numLoops);
        return left;
    }

    void printArray(int[] arr){
        for(int i = 0; i< arr.length; i++)
            System.out.print(" " + arr[i]);
        System.out.println();
    }

    public static void main(String[] args){
        RandomPickWeight rpw = new RandomPickWeight(new int[] {3,14,1,7, 8, 5, 6, 24, 21, 9, 11, 16, 3,14,1,7, 8, 5, 6, 24, 21, 9, 11, 16});
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            answer.add(rpw.pickIndex());
        }
        System.out.println(answer);
    }

}
