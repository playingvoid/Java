package recursionProblems.balancedParen;


import java.util.*;

public class balencedParen {
    public static void GenerateParent(int leftCount, int rightCount, StringBuilder slate, List<String> result){
        if(leftCount == 0 && rightCount == 0){
            result.add(slate.toString());
            //slate.setLength(slate.length() - 1);
            return;
        }
        if(leftCount > 0){
            GenerateParent(leftCount-1, rightCount, slate.append("("), result);
            slate.setLength(slate.length() - 1);
        }
        if(rightCount > leftCount){
            GenerateParent(leftCount, rightCount - 1, slate.append(")"), result);
            slate.setLength(slate.length() - 1);
        }

    }

    public static void allParnen(int n){
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        GenerateParent(n,n,sb, result);
        for(String s : result){
            System.out.println(s);
        }
    }
    public static void main(String[] args){
        allParnen(4);
    }
}
