package Spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class Token 
{
	enum TokenType
	{
		OPERATOR,
		CELL,
		INTEGER,
	}
	
	private String literal;
	private TokenType type;

	public Token(String literal)
	{
		this.literal = literal.trim();
		
		if("+".equals(this.literal) 
			|| "-".equals(this.literal)
			|| "*".equals(this.literal)
			|| "/".equals(this.literal))
			this.type = TokenType.OPERATOR;
		else if(Character.isLetter(this.literal.charAt(0)))
		{
			for(int i=1; i < this.literal.length(); i++)
			{
				if(!Character.isDigit(this.literal.charAt(i)))
					throw new RuntimeException("Invalid token: " + this.literal);
			}
			if(this.literal.length() <= 1)
				throw new RuntimeException("Invalid token: " + this.literal); 
			if(this.literal.charAt(1) == '0')
				throw new RuntimeException("Invalid token: " + this.literal);
			this.literal = this.literal.toUpperCase();
			this.type = TokenType.CELL;
		}
		else if(this.literal.matches("^-?\\d+$"))
		{
			this.type = TokenType.INTEGER;
		}
		else
			throw new RuntimeException("Invalid token: " + this.literal);
	}
	
	public TokenType getType() { return type;}
	public String getValue() {return literal;}

	public static List<Token> tokenize(String line, String delim)
	{
		List<Token> tokens = new ArrayList<Token>();
		if(line == null || line.isEmpty())
			return null;
		if(delim == null)
			delim = " ";
		for(String literal : line.split(delim))
		{
			tokens.add(new Token(literal));
		}
		
		return tokens;
	}
}
