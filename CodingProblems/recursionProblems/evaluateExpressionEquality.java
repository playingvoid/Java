package recursionProblems;

import java.util.*;

public class evaluateExpressionEquality {

    static HashMap<Character, Integer> Precedence = new HashMap<>();

    static
    {
        Precedence.put('j', 10);
        Precedence.put('*', 5);
        Precedence.put('+', 0);
    }

    public boolean precedenceCheck(Character c1, Character c2){
        return Precedence.get(c1) > Precedence.get(c2);
    }
    public static boolean isEvaluate(ArrayList<Character> operatorSequence, String numbers, int input){

        Stack<Character> postFix = new Stack<>();
        char[] numArray =  numbers.toCharArray();
        int i = 0, j = 0;
        boolean takeNum = true;
        ArrayList<Character> output = new ArrayList<>();
        while(i < numArray.length){
            if(takeNum){
                output.add(numArray[i]);
                i++;
                takeNum = false;
            } else {
                Character opt = operatorSequence.get(j);
                j++;
                while(!postFix.isEmpty() && Precedence.get(opt) < Precedence.get(postFix.peek()))
                {
                    output.add(postFix.pop());
                }
                postFix.push(opt);
                takeNum = true;
            }
        }
        while (!postFix.isEmpty()){
            output.add(postFix.pop());
        }

        Stack<Integer>  postFixEval = new Stack<>();

        for(i = 0; i < output.size(); i++){
            Character curr = output.get(i);
            if(curr == 'j' || curr == '*' || curr == '+'){
                Character operator = curr;
                Integer num1 = postFixEval.pop();
                Integer num2 = postFixEval.pop();
                if(operator == 'j'){
                    String num = num2 +""+num1;
                    postFixEval.push(Integer.parseInt(num));
                }
                else if(operator == '*'){
                    postFixEval.push(num1 * num2);
                } else {
                    postFixEval.push(num1 + num2);
                }
            } else {
                postFixEval.push(Integer.parseInt(curr.toString()));
            }
        }
        System.out.println(numbers + "" + operatorSequence + " => " + postFixEval.peek());
        return postFixEval.peek() == input;
    }

    public static char[] operators = new char[] {'j', '*', '+'};

    public static boolean isEvaluationPossible(ArrayList<Character>  operatorSequence, int remainingPlaces, String numbers, int input){
        if(remainingPlaces == 0){
            return isEvaluate(operatorSequence, numbers, input);
        }
        for(char operator : operators) {
            operatorSequence.add(operator);
            if(isEvaluationPossible(operatorSequence, remainingPlaces - 1, numbers, input))
                return true;
            operatorSequence.remove(operatorSequence.size() - 1);
        }
        return false;
    }

    public static boolean isEvaluationPossible(String numbers, int input){
        if(numbers == null || numbers == "")
            return  false;
        if(Integer.parseInt(numbers) == input)
            return  true;
        int remainingPlaces = numbers.length() - 1;
        ArrayList<Character>  operatorSequence = new ArrayList<>();
        return isEvaluationPossible(operatorSequence, remainingPlaces, numbers, input);
    }


    static String[] generate_all_expressions(String s, long target) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) return res.toArray(new String[0]);
        char[] path = new char[s.length() * 2];
        char[] digits = s.toCharArray();

        long n = 0;
        for(int i = 0; i < digits.length; i++){
            n = n * 10 + (digits[i]-'0');
            path[i] = digits[i];
            expressionsHelper(res, path, digits, i+1, i+1, 0, n, target);
        }
        return res.toArray(new String[0]);
    }

    public static void expressionsHelper(List<String> res, char[] path, char[] digits, int length, int pos, long prev, long cur, long target){
        if(pos == digits.length){
            if(prev+cur == target){
                res.add(new String(path, 0, length));
            }
        }else{
            long n = 0;
            int j = length+1;
            for(int i = pos; i < digits.length; i++){
                n = n * 10 + (digits[i]-'0');
                path[j++] = digits[i];
                path[length] = '*';
                expressionsHelper(res, path, digits, j, i+1, prev, cur*n, target);
                path[length] = '+';
                expressionsHelper(res, path, digits, j, i+1, prev+cur, n, target);
            }
        }
    }

    public static void main(String[] args){
        //System.out.println(isEvaluationPossible("123", 16));

        String[] output = generate_all_expressions("123", 7);
        for(String s : output)
            System.out.println(s);
    }
}
