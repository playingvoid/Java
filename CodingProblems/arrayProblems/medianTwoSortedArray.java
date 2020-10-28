package arrayProblems;

import javax.management.InvalidApplicationException;

public class medianTwoSortedArray {
    public static double findMedian(int[] arr1, int[] arr2) throws InvalidApplicationException {
        if(arr1.length >= arr2.length){
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        int kthPos = (arr1.length + arr2.length + 1)/2;
        boolean isOdd = (arr1.length + arr2.length) % 2 != 0;
        int leftP = 0, rightP = arr1.length, newP1 = -1;
        int newP2 = -1;

        while(leftP <= rightP) {
            int midP1 = leftP + (rightP - leftP) / 2;
            int midP2 = kthPos - midP1;
            int leftOfP1 = midP1 <= 0 ? Integer.MIN_VALUE : arr1[midP1-1];
            int rightOfP1 = midP1 >= arr1.length ? Integer.MAX_VALUE : arr1[midP1];
            int leftOfP2 = midP2 <= 0 ? Integer.MIN_VALUE : arr2[midP2-1];
            int rightOfP2 = midP2 >= arr2.length ? Integer.MAX_VALUE : arr2[midP2];

            if(leftOfP1 <= rightOfP2 && leftOfP2 <= rightOfP1){
                if(isOdd)
                    return Math.max(leftOfP1, leftOfP2);
                else
                    return (Math.max(leftOfP1, leftOfP2) + Math.min(rightOfP1, rightOfP2)) * 1.0 / 2;
            }
            else if (rightOfP1 < leftOfP2) {
                leftP = midP1 + 1;
            }
            else{
                rightP = midP1 - 1;
            }
        }

        throw new InvalidApplicationException("Programming Error");

    }
    public static void main(String[] args){

        int[] arr1 = new int[] {8, 12, 13, 18, 20, 23, 42};
        int[] arr2 = new int[] {7, 9, 14, 24, 25, 39, 45};

        try {
            System.out.println(findMedian(arr1, arr2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
