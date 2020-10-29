package graphProblems;

import java.util.*;

/*
Problem:
For given size of n*n chess board, find all non attacking placements of n queens

Category: Graph Backtracking

 */

public class nQueenNonAttackingPlacements {

    public static boolean isValidPlacement(int row, List<Integer> colPlacements){
        int newlyFilledColumnValue = colPlacements.get(colPlacements.size() - 1);
        for(int filledRow = 0; filledRow <row; filledRow++){
            int filledColumn = colPlacements.get(filledRow);

            if(newlyFilledColumnValue == filledColumn
               || Math.abs(filledRow - row) == Math.abs(filledColumn - newlyFilledColumnValue)){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPlacement2(int row, List<Integer> colPlacements){
        int rowId = colPlacements.size() - 1;
        for(int filledRow = 0; filledRow <row; filledRow++){
            int diff = Math.abs(colPlacements.get(filledRow) - colPlacements.get(rowId));

            if(diff == 0 //Column equal
               || diff == rowId - filledRow){ //or slope of line is 1
                return false;
            }
        }
        return true;
    }

    public static void findPlacements(int n, int row, List<Integer> colPlacements, List<List<Integer>> placements){
        if(row == n){
            placements.add(new ArrayList<>(colPlacements));
            return;
        }
        for(int col = 0; col < n; col++){
            colPlacements.add(col);
            if(isValidPlacement(row, colPlacements))
                findPlacements(n, row + 1, colPlacements, placements);
            colPlacements.remove(colPlacements.size() - 1);
        }
    }

    public static void findPlacements(int n){
        List<List<Integer>> placements = new ArrayList<>();
        List<Integer> colPlacements = new ArrayList<>();
        findPlacements(n, 0, colPlacements, placements);
        System.out.println("Solution for " + n + "Queens");
        for(List<Integer> solution : placements){
            System.out.println(solution);
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args){
        //isValidPlacement(2, new ArrayList<Integer>(Arrays.asList(1,3,2)));
        //findPlacements(1);
        //findPlacements(2);
        //findPlacements(3);
        findPlacements(4);
        findPlacements(5);
        findPlacements(6);
        findPlacements(7);
        findPlacements(8);
    }
}
