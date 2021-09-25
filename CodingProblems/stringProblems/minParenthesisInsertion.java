package stringProblems;

// https://www.geeksforgeeks.org/minimum-number-of-parentheses-to-be-added-to-make-it-valid/
// https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
public class minParenthesisInsertion {

    public static int minInsertions1(String s) {
        int rightNeeded = 0, needed = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(')
                rightNeeded += 2;
            else
                rightNeeded -= 1;

            if(rightNeeded == -2){
                needed += 1;
                rightNeeded = 0;
            } else if(rightNeeded == -1){
                needed += 2;
                rightNeeded = 0;
            }
        }


        return needed + rightNeeded;
    }

    static int minInsertions2(String s) {
        int ans = 0;
        int close = 0; // # of ')' needed.
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (--close < 0) {
                    // need to insert one '('
                    // ')' -> '()'
                    ++ans;
                    close += 2;
                }
            } else {
                if ((close & 1) != 0) {
                    // need to insert one ')'
                    // case '(()(' -> '(())('
                    --close;
                    ++ans;
                }
                close += 2; // need two ')'s
            }
        }
        return ans + close;
    }

    // best solution to understand
    public static int minInsertions(String s) {
        int left = 0; // left remaining to be adjusted
        int res = 0;  // adjustment needed till now
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++; // simple, we need to increment left needed to be adjusted by 1
            }
            else if (c == ')') {
                // if there is two consecutive right parenthesis
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    //there are two possibilities
                    // we do have at least one left paren to be adjusted, if yes, then adjust it by decreasing count
                    if (left > 0) {
                        left--;
                    } else { // if no, then we need one left paren for these two right paren
                        res++;
                    }
                    // forward already looked right paren
                    i++;
                } else {
                    // this is just the right paren followed by left paren
                    // we we still have left paren to adjust, then adjust one left paren to this right paren, but we need one more right paren to be added to result
                    if (left > 0) {
                        left--; // adjust left paren
                        res++;  // add one right paren
                    } else {
                        //there is no left paren to adjust, so we need one left and one right paren
                        res += 2;
                    }
                }
            }
        }
        res += left * 2;
        return res;
    }

    public static void main(String[] args){
        String input = "()())))()";
        System.out.println(minInsertions(input));
    }

}
