package fileSystemSimulation;

public class PWDCommand extends Command {

	protected PWDCommand(String[] commands) {
		super(commands);
	}

	@Override
	public Folder execute(Folder currentFolder) {
		Folder savedCurrentFolder = currentFolder;
		StringBuilder sb = new StringBuilder();
		while(currentFolder != null){
			sb.insert(0, "/" + currentFolder.name);
			currentFolder = currentFolder.parent;
		}
		System.out.println(sb.toString());
		return savedCurrentFolder;
	}
}
