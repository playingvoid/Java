//http://www.glassdoor.com/Interview/Implement-a-function-string-balanceParanthesis-string-s-which-given-a-string-s-consisting-of-some-parenthesis-returns-a-s-QTN_181725.htm
 //Implement a function string balanceParanthesis(string s   ); 
 //which given a string s consisting of some parenthesis returns a string s1 in which parenthesis are balanced 
 //and differences between s and s1 are minimum. 
 //Eg - "(ab(xy)u)2)" -> "(ab(xy)u)2" ")))(((" -> "" 
 
 int getRightParenCout(String input)
 {
	int count == 0;
	for(char c : input)
		if(c == ')') count++;
	return count;
 }
 String balancedParenthesis(String input)
 {
	int rightMatches = getRightParenCount(input);
	int rightRemaining = rightMatches;
	int leftMatches = 0;
	StringBuilder output = new StringBuilder();
	for(int i=0; i< input.lenght; i++)
	{
		char curr = input.charAt(i);
		if(curr == '(')
		{
			if(rightRemaining > 0 && rightMatches > 0)
			{
				leftMatches++;
				rightMatches--;
				output.append(curr);
			}
		}
		else if(curr == ')')
		{
			if(leftMatches > 0)
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
 
 Input:
 ()()()
 rr = 3, rm=3, lm =0
 -----
 rr=3, rm=2, lm=1, output = (
 -----
 rr=2, rm=2, lm=0, output = ()
 -----
 rr=2, rm=1, lm=1, output = ()(
 -----
 rr=1, rm=1, lm=0, output = ()()
 -----
 
 