package arrayProblems;

public class kthElementInTwoSortedArray {
    public static int findKth(int[] array1, int[] array2, int K) throws Exception {
        if(K <= 0 || K > array1.length + array2.length)
            throw new Exception("Invalid parameter K = " + K);
        if(array1.length > array2.length)
        {
            int[] temp = array1;
            array1 = array2;
            array2 = temp;
        }
        int left1 = 0, right1 = array1.length, p1Left, p1Right;
        int p2Left, p2Right;
        while(left1 <= right1){
            int partition1 = left1 + (right1 - left1) / 2;
            p1Left =  (partition1 - 1) < 0 ? Integer.MIN_VALUE : array1[partition1 - 1];
            p1Right = partition1 >= array1.length ? Integer.MAX_VALUE : array1[partition1];
            int partition2 = K - partition1;
            p2Left =  (partition2 - 1) < 0 ? Integer.MIN_VALUE : array2[partition2 - 1];
            p2Right = partition2>= array2.length ? Integer.MAX_VALUE : array2[partition2];

            if(p1Right < p2Left)
                left1 = partition1 + 1;
            else if(p2Right < p1Left)
                right1 = partition1 - 1;
            else
            {
                return Math.max(p1Left, p2Left);
            }
        }
        throw new Exception("Not possible - Programming error");
    }

    public static int findKth_two(int[] A, int[] B, int k){
        int b = Math.max(0, k - B.length);
        int t = Math.min(A.length, k);

        while(b < t){
            int x = b + ((t-b) / 2);
            int ax1 = ( x<= 0 ? Integer.MIN_VALUE : A[x-1]);
            int ax = (x >= A.length ? Integer.MAX_VALUE : A[x]);
            int bx1 = (k-x <=0 ? Integer.MIN_VALUE : B[k-x-1]);
            int bx = (k-x >= B.length ? Integer.MAX_VALUE : B[k-x]);

            if(ax < bx1){
                b = x + 1;
            } else if(ax1 > bx) {
                t = x-1;
            } else {
                return Math.max(ax1, bx1);
            }
        }

        int ab1 = b <=0 ? Integer.MIN_VALUE : A[b-1];
        int bb1 = k - b - 1 < 0 ? Integer.MIN_VALUE : B[k-b-1];
        return Math.max(ab1,  bb1);
    }

    public static void main(String[] args){
        int[] arr1 = new int[] {8, 12, 13, 18, 20, 23, 42};
        int[] arr2 = new int[] {7, 9, 14, 24, 25, 39, 45};

        try {
            System.out.println(findKth(arr1, arr2, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
