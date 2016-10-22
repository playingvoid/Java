package FileSystemSimulation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakeCommand extends Command {

	private static Set<String> validCommands;
	
	static {
		validCommands = new HashSet<>(Arrays.asList(new String[]{"mkdir", "mkfile"}));
	}
	
	protected MakeCommand(String[] commands) {
		super(commands);
	}

	@Override
	public Folder execute(Folder currentFolder) {
		validateCommand();
		switch(commands[0]){
		case "mkfile":
			currentFolder.addChildren(new File(commands[1]));
			break;
		case "mkdir":
			currentFolder.addChildren(new Folder(commands[1]));
			break;
		}
		return currentFolder;
	}
	
	@Override
	protected void validateCommand(){
		super.validateCommand();
		if(commands.length != 2 
		   || !validCommands.contains(commands[0])
		   || (commands[1] == null || commands[1] == ""))
			throw new RuntimeException("Invalid mk command");
	}
	
}
