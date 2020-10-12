package CodingProblems.arrayProblems.pairingParanthesis;

import java.util.Stack;

public class ParingParanthesis {
	static int getRightParenCout(String input) {
		int count = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ')')
				count++;
		}
		return count;
	}

	static String balancedParenthesis(String input) {
		int rightMatches = getRightParenCout(input);
		int rightRemaining = rightMatches;
		int leftMatches = 0;
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char curr = input.charAt(i);
			if (curr == '(') {
				if (rightRemaining > 0 && rightMatches > 0) {
					leftMatches++;
					rightMatches--;
					output.append(curr);
				}
			} else if (curr == ')') {
				if (leftMatches > 0) {
					leftMatches--;
					output.append(curr);
				}
				rightRemaining--;
			} else {
				output.append(curr);
			}
		}
		return output.toString();
	}
	
	// stack based
	public String MatchUsingStack(String input)
	{
		Stack<String> ph = new Stack<String>();
		for(Character c : input.toCharArray())
		{
			if(c.toString() == ")")
			{
				StringBuilder sb = new StringBuilder();
				while(ph.isEmpty() && ph.peek() != "(")
				{
					sb.insert(0, ph.pop());
				}
				// ph could be mepty
				// sb could be empty
				if(!ph.isEmpty() && ph.peek() != "(")
				{
					sb.append(c);
					sb.insert(0, ph.pop());
				}
				if(sb.toString().length() > 0)
					ph.push(sb.toString());
			}
			else 
			{
				ph.push(c.toString());
			}
		}
		
		StringBuilder finalOutput = new StringBuilder();
		while(!ph.isEmpty())
		{
			String ch = ph.pop();
			if(ch != "(" && ch != ")")
			{
				finalOutput.insert(0, ch);
			}
		}
		return finalOutput.toString();
	}

	public static void main(String[] args) {
		System.out.println("[" + ParingParanthesis.balancedParenthesis("(ab(xy)u)2)") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis("()()()") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis("(") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis(")") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis("()(") + "]");
		System.out.println("[" + ParingParanthesis.balancedParenthesis(")))(((") + "]");
	}
}
