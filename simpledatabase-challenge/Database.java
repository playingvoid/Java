import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

//Transaction class is just a wrapper on top of a Set which is supposed
//to contain only the variable names involved in a transaction
class Transaction
{
	private HashSet<String> variables;
	
	public Transaction()
	{
		variables = new HashSet<String>();
	}
	
	public void addVariables(String op)
	{
		variables.add(op);
	}
	
	public boolean containsVariable(String op)
	{
		return variables.contains(op);
	}
	
	public HashSet<String> getvariables()
	{
		return variables;
	}
	
	@Override
	public String toString()
	{
		return variables.toString();
	}
	
}

//Database class encapsulates the simple database implementation
public class Database 
{
	//transactions - Keeps the track of transaction opened while execution
	private Stack<Transaction> transactions;
	
	//values - Keeps track of values associated with a variable. Total number of values associated 
	//with a variable is derived from the number of transactions opened and whether a
	//variable participated in a transaction or not
	private HashMap<String, Stack<Integer>> values;
	
	//Keeps track of number of variables associated to a particular value
	private HashMap<Integer, Integer> numToValues;
	
	public Database()
	{
		transactions = new Stack<Transaction>();
		values = new HashMap<String, Stack<Integer>>();
		numToValues = new HashMap<Integer, Integer>();
	}
	
	public void printDataBase()
	{
		System.out.println("-------------------------------");
		System.out.println("\t\tValues: " + values);
		System.out.println("\t\tTransactions: " + transactions);
		System.out.println("\t\tnumToValues: " + numToValues);
		System.out.println("------------------------------");
	}
	
	public void setVariable(String name, Integer value)
	{
		boolean valueInCurrentTransaction = setVariableInCurrentTransaction(name);
		
		//For NUMEQUALTO
		manageOldAndNewValue(name, value);
		
		if(!values.containsKey(name))
			values.put(name, new Stack<Integer>());
		
		//If this is a new variable for this transaction or this variable 
		//never had a value then push the new value
		if(!valueInCurrentTransaction || values.get(name).isEmpty())
			values.get(name).push(value);
		else //otherwise replace the old value with new value
		{
			values.get(name).pop();
			values.get(name).push(value);
		}
	}
	
	public void unsetVariable(String name)
	{
		//if the variable has been set already then "set" the latest value for 
		//the given variable as null 
		if(values.containsKey(name))
		{
			setVariable(name, null);
		}
		else
			System.out.print("Can not unset Variable: " + name);
	}
	
	public Integer getVariable(String name)
	{
		Integer value = null;
		if(values.containsKey(name))
			value = values.get(name).peek();
		return value;
	}
	
	public void printGetVariable(String name)
	{
		Integer value = getVariable(name);
		if(null == value)
			System.out.println("NULL");
		else
			System.out.println(value);
	}
	
	public Integer numEqualTo(Integer value) 
	{
		return numToValues.getOrDefault(value, 0);
	}

	public void printNumToValues(Integer value)
	{
		System.out.println(numEqualTo(value));
	}
	
	public void begin() 
	{
		transactions.push(new Transaction());
	}
	
	//Commits updates each variable with its most recent value
	//if variable's most recent value is a NULL then deletes the variable entry altogether.
	//It updates the numEqualsTo dictionary for all variables
	//Also, Commit clears out all open transaction 
	public void commit()
	{
		if(transactions.isEmpty())
		{
			System.out.println("NO TRANSACTION");
			return;
		}
		
		numToValues = new HashMap<Integer, Integer>();
		
		Iterator<Map.Entry<String,Stack<Integer>>> iter = values.entrySet().iterator();
		while (iter.hasNext()) 
		{
		    Map.Entry<String,Stack<Integer>> entry = iter.next();
		    Integer mostRecentValue = entry.getValue().pop();
		    if(null != mostRecentValue)
		    {
		    	entry.setValue(new Stack<Integer>());
		    	entry.getValue().push(mostRecentValue);
		    	numToValues.put(mostRecentValue, numToValues.getOrDefault(mostRecentValue, 0) + 1);
		    }
		    else
		    	iter.remove();
		}
		
		//close all transactions, means no transaction exists any more
		transactions = new Stack<Transaction>();
	}
	
	//Roll back most recent values for the all the variables participated in 
	//most recent transaction. Also updates the numEqualsTo dictionary appropriately
	public void rollback()
	{
		if(transactions.isEmpty())
		{
			System.out.println("NO TRANSACTION");
			return;
		}
		
		Transaction mostRecentTansaction = transactions.pop();
		for(String variableName : mostRecentTansaction.getvariables())
		{
			//Pop out the most recent values, if key doesn't exists then it is a programming error
			Integer oldValue = values.get(variableName).pop();
			Integer newValue = null;
			//If no value remains then delete the variable entry
			if(values.get(variableName).isEmpty())
			{
				values.remove(variableName);
			}
			else
				newValue = values.get(variableName).peek();
			manageOldAndNewValue(newValue, oldValue);
		}
	}
	private void manageOldAndNewValue(String name, Integer value)
	{
		Integer oldValue = null;
		if(!values.isEmpty() && values.containsKey(name) && !values.get(name).isEmpty())
			oldValue = values.get(name).peek();
		manageOldAndNewValue(value, oldValue);
	}
	
	private void manageOldAndNewValue(Integer newValue, Integer oldValue)
	{
		if(null != newValue)
		{
			numToValues.put(newValue, numToValues.getOrDefault(newValue, 0) + 1);
		}
		if(null != oldValue)
		{
			Integer newCount = numToValues.get(oldValue) - 1;
			if(newCount == 0)
				numToValues.remove(oldValue);
			else
				numToValues.put(oldValue, newCount);
		}
	}
	
	//Adds the variable in the current transactions if there exists any
	//if this transaction already contains the variable or no transaction
	//is in progress then this function will return true.
	private boolean setVariableInCurrentTransaction(String name)
	{
		boolean valueInCurrentTransaction = true;
		//If there exists a transaction
		if(transactions.size() > 0)
		{
			//Check if value belongs to current transaction
			valueInCurrentTransaction = transactions.peek().containsVariable(name);
			if(!valueInCurrentTransaction)
				transactions.peek().addVariables(name);
		}
		return valueInCurrentTransaction;
	}
}
