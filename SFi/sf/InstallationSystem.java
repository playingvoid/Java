/*
 * Salesforce RPT interview
 * Interviewee: VARUN RAJ
 * Date: 10th October, 2016
 */

package salesforce;
import java.io.File;
import java.util.*;

public class InstallationSystem {
	
	private Set<String> installedComponents;
	private Map<String, Set<String>> dependencies;
	private Map<String, Set<String>> reverseDependencies;
	
	public InstallationSystem(){
		installedComponents = new HashSet<>();
		dependencies = new HashMap<>();
		reverseDependencies = new HashMap<>();
	}
	
	private void createDependencies(String[] dependency){
		if(dependency == null || dependency.length < 2)
			throw new RuntimeException("Invalid DEPEND Command");
		for(int i = 2; i < dependency.length; i++){
			createDependency(dependencies, dependency[i], dependency[1]);
			createDependency(reverseDependencies, dependency[1], dependency[i]);
		}
	}
	
	private void createDependency(Map<String, Set<String>> dependencyGraph, String leader, String follower){
		Set<String> leaders = dependencyGraph.getOrDefault(follower, new HashSet<String>());
		leaders.add(leader);
		dependencyGraph.put(follower, leaders);
	}
	
	private void installComponents(String[] commands){
		if(commands == null
		   || commands.length != 2)
			throw new RuntimeException("Invalid INSTALL Command");
		String compName = commands[1];
		if(installedComponents.contains(compName)){
			System.out.println("\t" + compName + " is already installed");
			return;
		}
		Set<String> visited = new HashSet<String>();
		visitDependecies(compName, visited);
	}
	
	private void visitDependecies(String command, Set<String> visited){
		if(installedComponents.contains(command))
			return;
		if(visited.contains(command)){
			throw new RuntimeException("\tWrong dependecy configuration");
		}
		
		visited.add(command);
		Set<String> leaderSet = dependencies.get(command);
		if(null != leaderSet){
			for(String leaderCommand : dependencies.get(command)){
				visitDependecies(leaderCommand, visited);
			}
		}
		installedComponents.add(command);
		System.out.println("\tInstalling " + command);
	}
	
	private void removeComponents(String[] commands){
		if(commands == null || commands.length != 2)
			throw new RuntimeException("\tInvalid REMOVE Command");
		String compName = commands[1];
		if(!installedComponents.contains(compName)){
			System.out.println("\t" +compName +" is not already installed" );
			return;
		}
		
		List<String> leaderRemoval = new LinkedList<String>();	
		HashSet<String> leaderVisit = new HashSet<String>();
		dfsVisit(dependencies, compName, leaderVisit, leaderRemoval);
		Collections.reverse(leaderRemoval);
		for(String component : leaderRemoval){
			Set<String> dependent = reverseDependencies.get(component);
			if(dependent == null || dependent.size() == 0){
				Set<String> leaderOfThisComponent = dependencies.get(component);
				if(leaderOfThisComponent != null){
					for(String leader : leaderOfThisComponent){
						reverseDependencies.get(leader).remove(component);
					}
				}
				dependencies.remove(component);	
				installedComponents.remove(component);
				System.out.println("\tRemoving " + component);
			} else {
				if(compName.equals(component))
					System.out.println("\t" + component + " still Needed");
			}
		}
	}
	
	private void listComponents(String[] commands){
		if(commands == null || commands.length > 1)
			throw new RuntimeException("Invalid LIST Command");
		for(String comp : installedComponents){
			System.out.println("\t" + comp);
		}
	}
	
	private void dfsVisit(Map<String, Set<String>> dependencyGraph, String component, HashSet<String> visited, List<String> orderedComponents){
		if(visited.contains(component)){
			return;
		}
		visited.add(component);
		Set<String> dependent = dependencyGraph.get(component);
		if(null != dependent){
			for(String comp : dependent){
				dfsVisit(dependencyGraph, comp, visited, orderedComponents);
			}		
		}
		orderedComponents.add(component);
	}
	
	
	public void readCommand(String cmdString){
		if(null == cmdString){
			throw new RuntimeException("Invalid command input");
		}
		String[] commands = cmdString.trim().replaceAll("\\s{2,}", " ").split(" ");
		switch(commands[0]){
			case "DEPEND":
				createDependencies(commands);
				break;
			case "INSTALL":
				installComponents(commands);
				break;
			case "REMOVE":
				removeComponents(commands);
				break;
			case "LIST":
				listComponents(commands);
				break;
			default:
				throw new RuntimeException("Bad Command");
		}
	}
	
	
	public static void main(String[] args){
		try{
			//File file = new File("salesforce.txt");
			//Scanner sc = new Scanner(file);
			Scanner sc = new Scanner(System.in);
			InstallationSystem is = new InstallationSystem();
			while(true){		
				try{				
					String cmdString = sc.nextLine();
					System.out.println(cmdString);
					if(cmdString.equals("END"))
						break;
					is.readCommand(cmdString);		
				} catch (Exception e){
					System.out.println(e.getMessage());
				}		
			}
			sc.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
