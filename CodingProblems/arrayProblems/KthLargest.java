package arrayProblems;

import java.util.*;

public class KthLargest {

    int partition(int[] nums, int left, int right){
        if(left == right)
            return left;
        Random r = new Random();
        int index = left + r.nextInt(right - left + 1);
        //swap index and right
        int temp = nums[right];
        nums[right] = nums[index];
        nums[index] = temp;
        //int temp;
        int i = left, j = right - 1;
        while(i <= j){
            if(nums[i] > nums[right]){
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
            else
                i++;
        }
        temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;
        return i;
    }
    public int findKthLargest(int[] nums, int k) {
        if( k > nums.length || k < 1)
            return -1;

        int desiredIndex = nums.length - k;

        int left = 0, right = nums.length -1;
        while(left <= right) {
            int p = partition(nums, left, right);
            if(p == desiredIndex)
                return nums[p];
            else if(desiredIndex > p){
                left = p + 1;
            }
            else {
                right = p - 1;
            }
        }
        return 0;
    }

    int findKthLargestPQ(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(int input : nums){
            if(pq.size()  < k)
                pq.add(input);
            else if(pq.size() == k && pq.peek() < input){
                pq.remove();
                pq.add(input);
            }

        }
        return pq.peek();
    }

    public static void main(String[] args){
        KthLargest lLargest = new KthLargest();
        //System.out.println(lLargest.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        System.out.println(lLargest.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        //System.out.println(lLargest.findKthLargestPQ(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        //System.out.println(lLargest.findKthLargestPQ(new int[]{3,2,1,5,6,4}, 2));
    }
}
