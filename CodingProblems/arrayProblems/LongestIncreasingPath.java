package arrayProblems;

public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {

        int[][] ans = new int[matrix.length][matrix[0].length];

        int maxAns = 0;
        for(int row = 0; row < matrix.length; row++)
        {
            for(int col = 0; col < matrix[0].length; col++)
            {
                if(ans[row][col] == 0)
                {
                    dfsMax(row, col, matrix, ans);
                    System.out.println("row: " + row + " col:" + col + " ans: " + ans[row][col]);
                    maxAns = Math.max(ans[row][col], maxAns);
                }
            }
        }

        return maxAns;

    }

    public int dfsMax(int row, int col, int[][] matix, int[][] ans)
    {
        if(ans[row][col] > 0)
            return ans[row][col];


        int maxAdjacentAns = 0;
        if(isValid(row+1, col, matix) && matix[row][col] < matix[row+1][col])
            maxAdjacentAns = Math.max(maxAdjacentAns, dfsMax(row+1, col, matix, ans));

        if(isValid(row-1, col, matix) && matix[row][col] < matix[row-1][col])
            maxAdjacentAns = Math.max(maxAdjacentAns, dfsMax(row-1, col, matix, ans));

        if(isValid(row, col+1, matix) && matix[row][col] < matix[row][col+1])
            maxAdjacentAns = Math.max(maxAdjacentAns, dfsMax(row, col+1, matix, ans));

        if(isValid(row, col-1, matix) && matix[row][col] < matix[row][col-1])
            maxAdjacentAns = Math.max(maxAdjacentAns, dfsMax(row, col-1, matix, ans));

        ans[row][col] = 1 + maxAdjacentAns;
        return ans[row][col];

    }

    boolean isValid(int row, int col, int[][] matrix)
    {
        return (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length);
    }

    public static void main(String[] args){
        LongestIncreasingPath lip = new LongestIncreasingPath();
        /*int[][] a = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };*/

        int[][] a = {
                {1, 2}
        };

        System.out.println(lip.longestIncreasingPath(a));
    }
}
