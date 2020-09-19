//Kth element in 2 sorted arrays

package KthElement;

public class KthElement {
	
	public static int findKth(int[] A, int[] B, int k){
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
		System.out.println("this is the first line");
		int[] arr1 = new int[] {8, 12, 13, 18, 20, 23, 42};
		int[] arr2 = new int[] {7, 9, 14, 24, 25, 39, 45};
		System.out.println(findKth(arr1, arr2, 7));
	}

}
