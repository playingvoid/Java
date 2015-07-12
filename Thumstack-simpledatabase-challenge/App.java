import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App 
{
	public static void main(String[] args) 
	{
		try(InputStreamReader in = new InputStreamReader(System.in))
		{
			try(BufferedReader br = new BufferedReader(in))
			{  
				Database database = new Database();
				String line;
			    while ((line = br.readLine()) != null && line.length() != 0)
			    {
			    	System.out.println("> " + line);
			    	Command input = Command.parseCommand(line);
			    	if(input == null)
			    		continue;
			    	switch(input)
			    	{
			    		case SET:
			    			database.setVariable(input.getName(), input.getValue());
			    			break;
			    		case GET:
			    			database.printGetVariable(input.getName());
			    			break;
			    		case UNSET:
			    			database.unsetVariable(input.getName());
			    			break;
			    		case NUMEQUALTO:
			    			database.printNumToValues(input.getValue());
			    			break;
			    		case BEGIN:
			    			database.begin();
			    			break;
			    		case COMMIT:
			    			database.commit();
			    			break;
			    		case ROLLBACK:
			    			database.rollback();
			    			break;
			    		case END:
			    			System.exit(0);
			    			break;		
			    	}
			    	//Uncomment following line to check the status of database internals after each command 
			    	database.printDataBase();	
			    }
			}
		}
		catch(IOException ie)  
		{  
			ie.printStackTrace(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
