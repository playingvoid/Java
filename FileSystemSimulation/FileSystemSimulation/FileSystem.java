package FileSystemSimulation;

import java.util.Scanner;

public class FileSystem {
	private Folder rootFolder, currentFolder;
	
	public FileSystem(){
		rootFolder = new Folder("root");
		currentFolder = rootFolder;
	}
	
	public static void main(String[] args){
		FileSystem  fs = new FileSystem();
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				System.out.print(fs.currentFolder.name + "> ");
				String cmdString = sc.nextLine();
				if(cmdString.equals("exit"))
					break;
				Command cmd = Command.parseCommand(cmdString);
				fs.currentFolder = cmd.execute(fs.currentFolder);	
			} catch (Exception e){
				System.out.println(e.getMessage());
			}		
		}
		sc.close();	
	}
}
