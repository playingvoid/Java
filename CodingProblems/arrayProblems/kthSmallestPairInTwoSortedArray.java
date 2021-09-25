package arrayProblems;

public class kthSmallestPairInTwoSortedArray {
    // Function to find k pairs with least sum such
    // that one element of a pair is from arr1[] and
    // other element is from arr2[]
    static void kSmallestPair(int arr1[], int n1, int arr2[],
                              int n2, int k)
    {
        if (k > n1*n2)
        {
            System.out.print("k pairs don't exist");
            return ;
        }

        // Stores current index in arr2[] for
        // every element of arr1[]. Initially
        // all values are considered 0.
        // Here current index is the index before
        // which all elements are considered as
        // part of output.
        int index2[] = new int[n1];

        while (k > 0)
        {
            // Initialize current pair sum as infinite
            int min_sum = Integer.MAX_VALUE;
            int min_index = 0;

            // To pick next pair, traverse for all
            // elements of arr1[], for every element, find
            // corresponding current element in arr2[] and
            // pick minimum of all formed pairs.
            for (int i1 = 0; i1 < n1; i1++)
            {
                // Check if current element of arr1[] plus
                // element of array2 to be used gives
                // minimum sum
                if (index2[i1] < n2 &&
                        arr1[i1] + arr2[index2[i1]] < min_sum)
                {
                    // Update index that gives minimum
                    min_index = i1;

                    // update minimum sum
                    min_sum = arr1[i1] + arr2[index2[i1]];
                }
            }

            System.out.print("(" + arr1[min_index] + ", " +
                    arr2[index2[min_index]]+ ") ");

            index2[min_index]++;
            k--;
        }
    }

    // Driver code
    public static void main (String[] args)
    {
        int arr1[] = {1, 2, 5, 7};
        int n1 = arr1.length;

        int arr2[] = {3, 5, 6};
        int n2 = arr2.length;

        int k = 10;
        //kSmallestPair( arr1, n1, arr2, n2, k);
        printKPairs(arr1, arr2, n1, n2, k);
    }

    static class _pair
    {
        int first, second;
    };

    // Function to print the K
// smallest pairs
    static void printKPairs(int a1[], int a2[],
                            int size1, int size2,
                            int k)
    {
        // if k is greater than
        // total pairs
        if (k > (size2 * size1))
        {
            System.out.print("k pairs don't exist\n");
            return;
        }

        // _pair _one keeps track of
        // 'first' in a1 and 'second' in a2
        // in _two, _two.first keeps track of
        // element in the a2[] and _two.second
        // in a1[]
        _pair _one = new _pair();
        _pair  _two = new _pair();
        _one.first = _one.second =
                _two.first = _two.second = 0;

        int cnt = 0;

        // Repeat the above process
        // till all K pairs are printed
        while (cnt < k)
        {
            // when both the pointers are
            // pointing to the same elements
            // (point 3)
            if (_one.first == _two.second &&
                    _two.first == _one.second)
            {
                if (a1[_one.first] <
                        a2[_one.second])
                {
                    System.out.print("[" +  a1[_one.first] +
                            ", " +  a2[_one.second] +
                            "] ");

                    // updates according to step 1
                    _one.second = (_one.second + 1) %
                            size2;

                    // see point 2
                    if (_one.second == 0)
                        _one.first = (_one.first + 1) %
                                size1;

                    // updates opposite to step 1
                    _two.second = (_two.second + 1) %
                            size1;

                    if (_two.second == 0)
                        _two.first = (_two.first + 1) %
                                size2;
                }
                else
                {
                    System.out.print("[" +  a2[_one.second] +
                            ", " +  a1[_one.first] +
                            "] ");

                    // updates according to rule 1
                    _one.first = (_one.first + 1) %
                            size1;

                    // see point 2
                    if (_one.first == 0)
                        _one.second = (_one.second + 1) %
                                size2;

                    // updates opposite to rule 1
                    _two.first = (_two.first + 1) %
                            size2;

                    // see point 2
                    if (_two.first == 0)

                        _two.second = (_two.second + 1) %
                                size1;
                }
            }

            // else update as
            // necessary (point 1)
            else if (a1[_one.first] +
                    a2[_one.second] <=
                    a2[_two.first] +
                            a1[_two.second])
            {
                if (a1[_one.first] <
                        a2[_one.second])
                {
                    System.out.print("[" +  a1[_one.first] +
                            ", " + a2[_one.second] +
                            "] ");

                    // updating according to rule 1
                    _one.second = ((_one.second + 1) %
                            size2);

                    // see point 2
                    if (_one.second == 0)
                        _one.first = (_one.first + 1) %
                                size1;
                }
                else
                {
                    System.out.print("[" +  a2[_one.second] +
                            ", " + a1[_one.first] +
                            "] ");

                    // updating according to rule 1
                    _one.first = ((_one.first + 1) %
                            size1);

                    // see point 2
                    if (_one.first == 0)
                        _one.second = (_one.second + 1) %
                                size2;
                }
            }
            else if (a1[_one.first] +
                    a2[_one.second] >
                    a2[_two.first] +
                            a1[_two.second])
            {
                if (a2[_two.first] <
                        a1[_two.second])
                {
                    System.out.print("[" +  a2[_two.first] +
                            ", " +  a1[_two.second] +
                            "] ");

                    // updating according to rule 1
                    _two.first = ((_two.first + 1) %
                            size2);

                    // see point 2
                    if (_two.first == 0)
                        _two.second = (_two.second + 1) %
                                size1;
                }
                else {
                    System.out.print("[" +  a1[_two.second] +
                            ", " +  a2[_two.first] +
                            "] ");

                    // updating according to rule 1
                    _two.second = ((_two.second + 1) %
                            size1);

                    // see point 2
                    if (_two.second == 0)
                        _two.first = (_two.first + 1) %
                                size1;
                }
            }
            cnt++;
        }
    }
}
