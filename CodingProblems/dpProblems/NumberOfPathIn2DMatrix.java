package dpProblems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NumberOfPathIn2DMatrix {
    public static int numberOfPaths(int[][] twoDMatrix){
        int[] pathMem = new int[twoDMatrix[0].length];
        Arrays.fill(pathMem, 0);
        pathMem[0] = 1;
        for(int row = 0; row < twoDMatrix.length; row++){
            for(int col = 0; col < twoDMatrix[0].length; col++){
                if(twoDMatrix[row][col] == 0){
                    pathMem[col] = 0;
                } else if(col > 0){
                    pathMem[col] += pathMem[col - 1];
                }
            }
        }
        return  pathMem[pathMem.length - 1];
    }
    public static void main(String[] args){
        int[][] twoDMatrix = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };

        System.out.println(numberOfPaths(twoDMatrix));
    }
}
