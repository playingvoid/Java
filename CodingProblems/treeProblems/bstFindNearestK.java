package treeProblems;
import java.util.*;

public class bstFindNearestK {
    public static void main(String[] args){
        List<Integer> someArray = new ArrayList<>(List.of(8, 10, -11, 16, 25, 0, 7, 9, 36, 19, -3, 6));

        Collections.sort(someArray, (a, b) -> Math.abs(11-a) - Math.abs(11-b));

        System.out.println(someArray);

    }
}
