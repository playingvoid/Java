package arrayProblems;

import java.util.*;

public class NextPermutation {
    public void nextPermutation(int[] nums) {

        int pivotIndex = findPivot(nums);

        if(pivotIndex >=0){
            int justUpperToPivotIndex = binarySearchJustToPivot2(nums, pivotIndex);
            int temp = nums[pivotIndex];
            nums[pivotIndex] = nums[justUpperToPivotIndex];
            nums[justUpperToPivotIndex] = temp;
        }

        reverse(nums, pivotIndex+1);
    }

    private void reverse(int[] nums, int left){
        int right = nums.length - 1;
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    private int findPivot(int[] nums)
    {
        int lastIndex = nums.length - 2;
        while(lastIndex >= 0){
            if(nums[lastIndex] < nums[lastIndex + 1])
                return lastIndex;
            lastIndex--;
        }
        return lastIndex;
    }
    private int binarySearchJustToPivot2(int nums[], int pivotIndex){
        int searchValue = nums[pivotIndex];
        int left = pivotIndex + 1;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            System.out.println("left = " + left + ",  right = " + right + ",  mid = " + mid + ",  num mid: " + nums[mid]);
            if(nums[mid] > searchValue){
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }
        return right;
    }

    private int binarySearchJustToPivot(int nums[], int pivotIndex){
        int searchValue = nums[pivotIndex];
        int left = pivotIndex + 1;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > searchValue){
                right = mid - 1;
            }
            else
            {
                left = mid + 1;
            }
        }
        return left;
    }
    public static void main(String[] args){
        NextPermutation np = new NextPermutation();
        int[] nums = new int[]{3,2,6,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,7,7,6,6,6,6,5,5,5,5,5,4,4,4,4,3,3,3,2,2,2,1,1,1,1};
        np.nextPermutation(nums);
        for(int i : nums){
            System.out.print(i + ",");
        }
    }
}
