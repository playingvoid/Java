package FileSystemSimulation;

public abstract class Command {
	protected String[] commands;
	/*
	 * Command Factory
	 */
	public static final Command parseCommand(String cmdString){
		if(null == cmdString){
			throw new RuntimeException("Invalid command input");
		}
		String[] commands = cmdString.trim().replaceAll("\\s{2,}", " ").split(" ");
		switch(commands[0]){
			case "ls":
				return new ListCommand(commands);
			case "pwd":
				return new PWDCommand(commands);
			case "mkdir":
			case "mkfile":
				return new MakeCommand(commands);
			case "cd":
				return new CDCommand(commands);
			default:
				throw new RuntimeException("Bad Command");
		}
	}
	
	public abstract Folder execute(Folder currentFolder);
	
	protected Command(String[] commands){
		this.commands = commands;
	}
	
	protected void validateCommand(){
		if(commands == null || commands.length == 0){
			throw new RuntimeException("Invalid command");
		}
	}
}
