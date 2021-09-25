package dpProblems;
import java.util.*;

public class RopeCutting {

    public static long maximumProduct(int number){
        long[] ropeCutMem = new long[number + 1];

        if(number < 2)
            return 0;
        if(number == 2)
            return 1;
        if(number == 3)
            return 2;

        ropeCutMem[0] = 0;
        ropeCutMem[1] = 1;
        ropeCutMem[2] = 2;
        ropeCutMem[3] = 3;
        for(int i = 4; i <= number; i++){
            ropeCutMem[i] = 0;
            for(int j = 1; j <= i /2; j++){
                ropeCutMem[i] = Math.max(ropeCutMem[i], ropeCutMem[j] * ropeCutMem[i - j]);
            }
        }
        return ropeCutMem[number];
    }

    public static void main(String[] args){
        System.out.println(maximumProduct(109));
    }
}
