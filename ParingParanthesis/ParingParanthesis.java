//http://www.glassdoor.com/Interview/Implement-a-function-string-balanceParanthesis-string-s-which-given-a-string-s-consisting-of-some-parenthesis-returns-a-s-QTN_181725.htm
 //Implement a function string balanceParanthesis(string s   ); 
 //which given a string s consisting of some parenthesis returns a string s1 in which parenthesis are balanced 
 //and differences between s and s1 are minimum. 
 //Eg - "(ab(xy)u)2)" -> "(ab(xy)u)2" ")))(((" -> "" 
 
public class ParingParanthesis 
{
	static int getRightParenCout(String input) 
	{
		int count = 0;
		for (int i = 0; i < input.length(); i++) 
		{
			if (input.charAt(i) == ')')
				count++;
		}
		return count;
	}

	static String balancedParenthesis(String input) 
	{
		int rightMatches = getRightParenCout(input);
		int rightRemaining = rightMatches;
		int leftMatches = 0;
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < input.length(); i++) 
		{
			char curr = input.charAt(i);
			if (curr == '(') 
			{
				if (rightRemaining > 0 && rightMatches > 0) 
				{
					leftMatches++;
					rightMatches--;
					output.append(curr);
				}
			} 
			else if (curr == ')') 
			{
				if (leftMatches > 0) 
				{
					leftMatches--;
					output.append(curr);
				}
				rightRemaining--;
			} 
			else 
			{
				output.append(curr);
			}
		}
		return output.toString();
	}

	public static void main(String[] args) 
	{
		System.out.println("[" + ParingParanthesis.balancedParenthesis("(ab(xy)u)2)") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis("()()()") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis("(") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis(")") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis("()(") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis(")))(((") + "]");
	}
}

 