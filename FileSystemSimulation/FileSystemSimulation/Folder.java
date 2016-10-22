package fileSystemSimulation;

import java.util.LinkedHashMap;

public class Folder {
	protected String name;
	protected Folder parent;
	protected LinkedHashMap<String, Folder> children;
	
	protected Folder(String name){
		this.name = name;
		parent = null;
		children = new LinkedHashMap<>();
	}
	
	public void addChildren(Folder folder){
		if(folder == null)
			throw new RuntimeException("Can not add a null folder");	
		if(children.containsKey(folder.name))
			throw new RuntimeException("A folder with name already exists");
		
		folder.parent = this;
		children.put(folder.name, folder);
	}
}