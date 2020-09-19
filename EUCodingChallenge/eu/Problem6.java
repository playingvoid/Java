package earnUp;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

import javax.naming.OperationNotSupportedException;

class Graph {
	/*
	 * Not writing a "Node type", because a "Node" can be easily simulated
	 * by an Integer type for the given problem and all its use cases private
	 * static class Node{
	 * 
	 * }
	 */
	private static final Integer MAX_NODE_VALUE = 60000;
	private Map<Integer, Set<Integer>> graphNodes;

	public Graph() {
		graphNodes = new HashMap<>();
	}

	public boolean addNode(Integer nodeValue) {
		if (!validateNodeValue(nodeValue))
			throw new InvalidParameterException("Invalid value for the input parameter");
		if (graphNodes.containsKey(nodeValue))
			return false;
		graphNodes.put(nodeValue, new HashSet<>());
		return true;
	}

	public boolean addChild(Integer parent, Integer child) {
		if (!validateNodeValue(parent))
			throw new InvalidParameterException("Invalid value for parent");
		if (!validateNodeValue(child))
			throw new InvalidParameterException("Invalid value for child");
		if (!graphNodes.containsKey(parent))
			throw new IllegalArgumentException("Parent node is not found in graph");
		if (!graphNodes.containsKey(child))
			throw new IllegalArgumentException("Child node is not found in graph");
		if (parent.equals(child))
			throw new IllegalStateException(
					"Possible cycle(Self loop) detected - Parent and Child can not have same value");
		// Return false if child is already added to parent
		if (graphNodes.get(parent).contains(child))
			return false;

		Queue<Integer> bfsQueue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		// Try detecting if there already exists a path from child to parent -
		// if it exists then this operation would cause formation of a cycle.
		bfsQueue.add(child);
		visited.add(child);
		while (true) {
			int nodeInThisLevel = bfsQueue.size();
			if (nodeInThisLevel == 0)
				break;
			while (nodeInThisLevel > 0) {
				Integer thisNode = bfsQueue.remove();
				nodeInThisLevel--;
				if (graphNodes.get(thisNode).contains(parent))
					throw new IllegalStateException(
							"Possible cycle(Self loop) detected - Path from child to parent already exists");
				graphNodes.get(thisNode).forEach(thisNodeChild -> {
					if (!visited.contains(thisNodeChild)) {
						bfsQueue.add(thisNodeChild);
						visited.add(thisNodeChild);
					}
				});
			}
		}
		graphNodes.get(parent).add(child);
		return true;
	}

	public void print() {
		for (Map.Entry<Integer, Set<Integer>> entry : graphNodes.entrySet()) {
			System.out.print(entry.getKey() + "-> ");
			if (entry.getValue().size() > 0) {
				String commaSeparatedNumbers = entry.getValue().stream().map(i -> i.toString())
						.collect(Collectors.joining(", "));
				System.out.print(commaSeparatedNumbers);
			} else {
				System.out.print("No Children");
			}
			System.out.println();
		}
	}

	private static boolean validateNodeValue(Integer nodeValue) {
		return nodeValue != null && nodeValue >= 0 && nodeValue <= MAX_NODE_VALUE;
	}
}

public class Problem6 {
	private static final String INV_OPN_MSG = "Invalid operation/command";
	
	// Read input from System.Input stream
	// Instructions
	// 1. To add a node: ADD <Node_Value>, e.g. ADD 10
	// 2. To make child of a parent: MKCHILD <Parent_Node_Value> <Child_Node_Value>, e.g MKCHILD 10 12
	// 3. To print the graph: PRINT
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Graph graph = new Graph();
		while (true) {
			try {
				String cmdString = sc.nextLine();
				if (cmdString.equals("EXIT"))
					break;
				String[] commands = cmdString.split("\\s+");
				if(commands.length >= 1){
					if(commands[0].equals("ADD")){
						processAddNode(commands, graph);
					} else if(commands[0].equals("MKCHILD")){
						processMkChildAddNode(commands, graph);
					} else if(commands[0].equals("PRINT")){
						processPrint(commands, graph);
					} else {
						throw new OperationNotSupportedException(INV_OPN_MSG);
					}
				} else {
					throw new OperationNotSupportedException(INV_OPN_MSG);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		sc.close();
	}
	
	private static void processAddNode(String[] command, Graph graph) throws OperationNotSupportedException{
		if(command.length != 2)
			throw new OperationNotSupportedException(INV_OPN_MSG);
		Integer nodeValue = Integer.parseInt(command[1]);
		graph.addNode(nodeValue);
	}
	
	private static void processMkChildAddNode(String[] command, Graph graph) throws OperationNotSupportedException{
		if(command.length != 3)
			throw new OperationNotSupportedException(INV_OPN_MSG);
		Integer parent = Integer.parseInt(command[1]);
		Integer child = Integer.parseInt(command[2]);
		graph.addChild(parent, child);
	}
	
	private static void processPrint(String[] command, Graph graph) throws OperationNotSupportedException {
		if(command.length != 1)
			throw new OperationNotSupportedException(INV_OPN_MSG);
		graph.print();
	}
}
