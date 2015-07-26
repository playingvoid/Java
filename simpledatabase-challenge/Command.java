public enum Command 
{
	GET,
	SET,
	UNSET,
	BEGIN,
	COMMIT,
	ROLLBACK,
	NUMEQUALTO,
	END;
	
	private String name;
	private Integer value;
	private Command()	{ value = null; }
	public String getName() { return name; }
	public Integer getValue() { return value; }
	private void setValue(String name, String value) 
	{ 
		this.name = name;
		if(null != value)
			this.value = Integer.valueOf(value); 
	}
	public static Command parseCommand(String line)
	{
		String[] splitted = line.trim().split(" ");
		Command command = null;
		try
		{
			command = Command.valueOf(splitted[0].toUpperCase());

			switch(command)
			{
				case SET:
					command.setValue(splitted[1], splitted[2]);
					break;
				case GET:
				case UNSET:
					command.setValue(splitted[1], null);
					break;
				case NUMEQUALTO:
					command.setValue(null, splitted[1]);
					break;
				case BEGIN:
				case COMMIT:
				case ROLLBACK:
				case END:
					break;
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Invalid Command: " + line);
			command = null;
		}
		return command;
	}
}
