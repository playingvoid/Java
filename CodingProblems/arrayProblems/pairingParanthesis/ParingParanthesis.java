package arrayProblems.pairingParanthesis;

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
	public string MatchUsingStack(string input)
	{
		Stack<string> ph = new Stack<string>();
		foreach(char c in input)
		{
			if(c.toString() == ")")
			{
				StringBuilder sb = new StringBuilder();
				while(ph.isEmpty() && ph.Peek() != "(")
				{
					sb.Insert(0, ph.Pop());
				}
				// ph could be mepty
				// sb could be empty
				if(!ph.IsEmpty() && ph.Peek() != "(")
				{
					sb.Append(c);
					sb.Insert(0, ph.Pop());
				}
				if(sb.ToString().Length() > 0)
					ph.push(sb.ToString());
			}
			else 
			{
				ph.push(c)
			}
		}
		
		StringBuilder final = new StringBuilder();
		while(!ph.isEmpty())
		{
			string ch = ph.Pop();
			if(ch != "(" && ch != ")")
			{
				final.Insert(0, ch);
			}
		}
		return final.ToString();
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
