package FileSystemSimulation;

public class File extends Folder {
	public File(String name){
		super(name);
		children.clear();
	}
	
	@Override
	public void addChildren(Folder folder){
		throw new RuntimeException("Can not create children under a File type");
	}
}