package fileSystemSimulation;

public class ListCommand extends Command {	
	protected ListCommand(String[] commands) {
		super(commands);
	}
	
	@Override
	protected void validateCommand(){
		super.validateCommand();
		if(commands.length > 2)
			throw new RuntimeException("Invalid list command");
		
		if(commands.length == 2 && !commands[1].equals("-l"))
			throw new RuntimeException("Invalid list command option");
	}

	@Override
	public Folder execute(Folder currentFolder){
		validateCommand();
		
		/*
		 Comparator<Employee> byEmployeeNumber = (e1, e2) -> Integer.compare(
            e1.getEmployeeNumber(), e2.getEmployeeNumber()); 
		 
		 */
		if(commands.length == 2 && commands[1].equals("-l")){
			currentFolder.children.keySet()
						 .stream()
						 .sorted((child1, child2) -> child1.compareTo(child2))
						 .forEach(child -> System.out.println(child));
		
		} else {
			currentFolder.children.keySet()
						 .stream()
						 .forEach(child -> System.out.println(child));
		}
		
		return currentFolder;
	}
}