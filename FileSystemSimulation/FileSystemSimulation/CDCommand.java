package fileSystemSimulation;

public class CDCommand extends Command {

	protected CDCommand(String[] commands) {
		super(commands);
	}
	
	protected void validateCommand(){
		if(commands.length != 2
		   || commands[1] == null 
		   || commands[1].equals(""))
			throw new RuntimeException("Not a valid CD command");
		
	}

	@Override
	public Folder execute(Folder currentFolder) {		
		switch(commands[1]){
			case "..":
				if(null == currentFolder.parent)
					throw new RuntimeException("no parent folder exists");
				return currentFolder.parent;
			case ".":
				return currentFolder;
			case "/":
				return getTopLevelParent(currentFolder);
			default:
				return goToNextLevelFolder(currentFolder, commands[1]);
		}

	}
	
	private static Folder getTopLevelParent(Folder folder){
		Folder parent = folder;
		while(null != parent){
			folder = parent;
			parent = folder.parent;
		}
		return folder;
	}
	
	private static Folder goToNextLevelFolder(Folder folder, String childName){
		Folder nextLevel = folder.children.get(childName);
		if(null == nextLevel
		   || (nextLevel instanceof File))
			throw new RuntimeException("Not a valid folder option");
		return nextLevel;
	}
}
