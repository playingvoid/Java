package arrayProblems;

/*
PROBLEM STATEMENT:
Given an array which contain a sequence of good commits followed by a sequence of bad commits,
find the (index of) last good commit.
Technique: Binary search
 */

public class lastGoodCommit {
    public static int getLastGoodCommit(char[] commitArray){
        if(commitArray == null || commitArray.length == 0)
            return -1;
        int leftIndex = 0, rightIndex = commitArray.length - 1, midIndex = -1;
        while(leftIndex <= rightIndex){
            midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if(commitArray[midIndex] == 'G'){
                leftIndex = midIndex + 1;
            }
            else {
                rightIndex = midIndex - 1;
            }
        }
        return leftIndex - 1;
    }

    public static void main(String[] args){
        //char[] commits = ;
        System.out.println(getLastGoodCommit(new char[]{}));
        System.out.println(getLastGoodCommit(new char[]{'G'}));
        System.out.println(getLastGoodCommit(new char[]{'B'}));
        System.out.println(getLastGoodCommit(new char[]{'G','G'}));
        System.out.println(getLastGoodCommit(new char[]{'B','B'}));
        System.out.println(getLastGoodCommit(new char[]{'G','G', 'G'}));
        System.out.println(getLastGoodCommit(new char[]{'B','B', 'B'}));
        System.out.println(getLastGoodCommit(new char[]{'G','B', 'B'}));
        System.out.println(getLastGoodCommit(new char[]{'G','G', 'B'}));
        System.out.println(getLastGoodCommit(new char[]{'G','G', 'B', 'B', 'B'}));
    }
}
