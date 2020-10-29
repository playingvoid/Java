package dpProblems;
/*
PROBLEM: print nth fibonacci
1 [1st], 1 [2nd], 2 [3rd], 3 [4th], 5 [5th], 8 [6th], 13 [7th], 21 [8th] ... and so on
*/

public class nthFibonacci {
    public static long nthFibonacci(int n){
        if(n <= 0)
            return 0;
        long prev = 1;
        long curr = 1;
        for(int i = 3; i <= n; i++){
            curr = prev + curr;
            prev = curr - prev;
        }
        return curr;
    }

    public static void main(String[] args){
        System.out.println(nthFibonacci(1));
        System.out.println(nthFibonacci(2));
        System.out.println(nthFibonacci(3));
        System.out.println(nthFibonacci(4));
        System.out.println(nthFibonacci(5));
        System.out.println(nthFibonacci(6));
        System.out.println(nthFibonacci(7));
        System.out.println(nthFibonacci(8));
        System.out.println(nthFibonacci(9));
        System.out.println(nthFibonacci(10));
        System.out.println(nthFibonacci(11));
        System.out.println(nthFibonacci(20));
        System.out.println(nthFibonacci(50));
    }

}
