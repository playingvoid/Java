package Spreadsheet;

public class ExpressionUtil 
{
	public static Double evaluate(Double operand1, Double operand2, String operator)
	{
		if(null == operand1 || null == operand2 || operator == null || operator.isEmpty())
			throw new RuntimeException("Invalid expression evaluation");
		
		if("+".equals(operator))
			return operand1 + operand2;
		if("-".equals(operator))
			return operand1 - operand2;
		if("/".equals(operator))
			return operand1 / operand2;
		if("*".equals(operator))
			return operand1 * operand2;
		
		throw new RuntimeException("Invalid Operator " + operator);
	}

}
