//https://www.geeksforgeeks.org/sum-products-possible-subsets/

/*
static int SumOfProduct(int[] input, int index)
{
	if (index == input.Length)
		return 0;
	return (input[index] + 1) * SumOfProduct(input, index + 1) + input[index];
}

static int SumOfProduct2(int[] arr)
{
	int ans = 1;
	for (int i = 0; i < arr.Length; ++i)
		ans = ans * (arr[i] + 1);
	return ans - 1;
}
*/
