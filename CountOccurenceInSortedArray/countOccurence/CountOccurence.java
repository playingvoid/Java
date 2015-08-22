package countOccurence;

import java.util.StringJoiner;

/*
Use Binary Search-
1) Use Binary search to get the left boundary, say 'left': Complexity O(LogN)
2) Use Binary search to get the right boundary, say 'right': Complexity O(LogN)
3) Desired Count = right - left + 1;

Total Compleity = 2 * O(LogN) => O(LogN) 
*/
public class CountOccurence
{
	//Returns the count of occurences of 'searchThis'
	//null handling is not done
	static int count(int arr[], int searchThis, int arraySize)
	{
		int left; // the first occurence of, left boundary
		int right; // the last occurence, right boundary

		//get the index of first occurrence (left boundary) of 'searchThis'
		//in the entire array
		left = firstPosition(arr, 0, arraySize-1, searchThis);

		//If doesn't exists, means count is 0
		if(left == -1)
			return 0;

		//now get the right boundary  
		right = lastPosition(arr, left, arraySize - 1, searchThis, arraySize);     

		//Count 
		return right - left + 1;
	}

	// Binary search for the first postion of 'searchThis'
	static int firstPosition(int arr[], int low, int high, int searchThis)
	{
		if(high >= low)
		{
			int mid = (low + high)/2;  
			if( ( mid == 0 || searchThis > arr[mid-1]) && arr[mid] == searchThis)
				return mid;
			else if(searchThis > arr[mid])
				return firstPosition(arr, (mid + 1), high, searchThis);
			else
				return firstPosition(arr, low, (mid -1), searchThis);
		}
		return -1;
	}

	// Binary search for the last position of 'searchThis'
	static int lastPosition(int arr[], int low, int high, int searchThis, int arraySize)
	{
		if(high >= low)
		{
			int mid = (low + high)/2; 
			if( ( mid == arraySize-1 || searchThis < arr[mid+1]) && arr[mid] == searchThis )
				return mid;
			else if(searchThis < arr[mid])
				return lastPosition(arr, low, (mid -1), searchThis, arraySize);
			else
				return lastPosition(arr, (mid + 1), high, searchThis, arraySize);      
		}
		return -1;
	}

	private static String arrayToString(int[] input)
	{
		if(input == null) return null;
		StringJoiner sj = new StringJoiner(",", "[", "]");
		for(int i : input)
			sj.add(Integer.toString(i));
		return sj.toString();
	}

	public static void main(String []args)
	{
		int[] arr = new int[]{};
		System.out.println(arrayToString(arr) + " for 0:" +  count(arr, 0, arr.length));
		
		arr = new int[]{1, 2, 2, 3, 3, 3, 3};
		System.out.println(arrayToString(arr) + " for 0:" +  count(arr, 0, arr.length));	
		System.out.println(arrayToString(arr) + " for 1:" +  count(arr, 1, arr.length));
		System.out.println(arrayToString(arr) + " for 2:" +  count(arr, 2, arr.length));
		System.out.println(arrayToString(arr) + " for 3:" +  count(arr, 3, arr.length));
		System.out.println(arrayToString(arr) + " for 4:" +  count(arr, 4, arr.length));

		arr = new int[]{0};
		System.out.println(arrayToString(arr) + "  for -1:" +  count(arr, -1, arr.length));	
		System.out.println(arrayToString(arr) + "  for 0:" +  count(arr, 0, arr.length));		
		System.out.println(arrayToString(arr) + "  for 1:" +  count(arr, 1, arr.length));	
		
		arr = new int[]{-2, -1, 0, 0, 0, 3, 3, 3};
		System.out.println(arrayToString(arr) + "  for 0:" +  count(arr, 0, arr.length));
		
		arr = new int[]{-2, -1, 0, 3, 3, 3};
		System.out.println(arrayToString(arr) + "  for 0:" +  count(arr, 0, arr.length));
	}
}
