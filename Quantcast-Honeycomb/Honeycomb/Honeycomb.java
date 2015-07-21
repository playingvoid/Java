package Honeycomb;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//A Node abstracts out a hexagonal node in Honeycomb graph
class Node
{	
	//Id of the node. It is to identify one node and to distinguish it from another 
	private int id;
	
	//The layer in which this node exists
	private int layer;
	
	//The value of this node
	private char value;
	
	//All the connected node to this node in Honeycomb
	private List<Node> adjacentNodes;
	
	public Node(int id, int layer, char value)
	{
		this.id = id;
		this.layer = layer;
		this.value = value;
		adjacentNodes = new ArrayList<Node>();
	}
	
	public int getId() { return id; }
	
	public char getValue() { return value; }
	
	public List<Node> getAdjacentNodes() { return adjacentNodes; }
	
	public void addAdjacentNode(Node adjNode)
	{
		adjacentNodes.add(adjNode);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Node n : adjacentNodes)
			sb.append(n.id + ",");
		return id + "->" + sb.toString() + " (" + value + ")" + "[" + layer+ "]";
	}
}

//Abstraction of Honeycomb. Actually a graph of all the nodes in honeycomb
public class Honeycomb 
{
	//Total number of layers in Honeycomb 
	private int layers;
	
	//All the node, gets populated as a node encountered in input file 
	private Node[] combArray;
	
	//A hashmap, which stores all the nodes having same Character in Honeycomb
	//it is just to facilitate the word search without going through all the nodes in Honeycomb first
	private HashMap<Character, List<Integer>> charNodeMap;
	
	public Honeycomb(String honeycombFile)
	{
		charNodeMap = new HashMap<Character, List<Integer>>();
		try
		{
			BufferedReader br = 
			        new BufferedReader(new FileReader(honeycombFile));
			setLayerCount(br);
			//total number of nodes based on input layers
			int totalNodes = 1 + 6 * ((layers - 1) * layers)/2;
			combArray = new Node[totalNodes];
			initializeComb(br);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void print()
	{
		for(Node n : combArray)
			System.out.println(n);
	}
	
	public boolean findWord(String word)
	{
		if(word == null || word.length() == 0)
			return false;
		List<Integer> startNodeIds = charNodeMap.getOrDefault(word.charAt(0), null);
		if(startNodeIds == null)
			return false;
		HashSet<Integer> visited = new HashSet<Integer>();
		for(Integer nodeId : startNodeIds)
		{
			Node currNode = combArray[nodeId];	
			if(findWord(currNode, word, visited))
				return true;	
		}
		return false;
	}
	
	public boolean findWord(Node currNode, String word, HashSet<Integer> visited)
	{
		if(word.length() == 0) return true;
		if(word.length() == 1 && word.charAt(0) == currNode.getValue()) return true;
		if(word.charAt(0) != currNode.getValue())
			return false;
		
		visited.add(currNode.getId());
		for(Node adjNode : currNode.getAdjacentNodes())
		{
			if(!visited.contains(adjNode.getId()))
			{
				if(findWord(adjNode, word.substring(1), visited))
					return true;
			}
		}
		visited.remove(currNode.getId());
		return false;
	}
	
	//Reads the first line from the file and set the layers value
	private void setLayerCount(BufferedReader br) throws IOException
	{
		String line = br.readLine();
		layers = Integer.parseInt(line);
	}
	
	//For any line, it treats it as a layer and add it to the combArray starting from
	//the input offset index
	private void addLayer(String inputNodes, int layer, int offset)
	{
		for(int i=0; i< inputNodes.length(); i++)
		{
			combArray[offset + i] = new Node(offset + i, layer, inputNodes.charAt(i));
			PopulateCharIntegerMap(inputNodes.charAt(i), offset + i);
		}
	}
	
	//This function connects the node in the previous and the current layer.
	//It also connects all the nodes with in a layer
	private void connectNodesInLayer(int prevlayerStartIndex, int currentLayerStartIndex, int layer)
	{
		int currentLayerLength = layer * 6;
		
		//1. First connect the nodes with in this layer, first loop to all the nodes and connect
		//it to the next node in the layer
		for(int i = currentLayerStartIndex; 
				i < currentLayerStartIndex + currentLayerLength - 1; 
				i++ )
		{
			connectNodes(combArray[i], combArray[i + 1]);
		}
		//Connect the first and then last node in the layer
		connectNodes(combArray[currentLayerStartIndex], 
				combArray[currentLayerStartIndex + currentLayerLength -1]);
		
		//2. Connect the layers, previous and current
		//2.a. If the previous layer was the center most node, then connect it to all the nodes around it
		if(prevlayerStartIndex == 0)
		{
			//Loop on the node in current layer and connect it to centermost node
			for(int i = currentLayerStartIndex; i < currentLayerStartIndex + currentLayerLength; i++ )
			{
				connectNodes(combArray[0], combArray[i]);
			}
		}
		//2.b. If the previous layer was not of center most node, then identify the corner vs simple node
		//in previous layer. 
		//Corner node: defined for which for which nodeoffset in the layer is divisible by layer
		//			   ex: in layer 3, node t position 0, 3, 6 .. are corner nodes
		//Simple node: node which are not corner node.
		//For corner node in previous layer connect it to three adjacent node in current layer
		//For simple node in previous layer connect it to two adjacen node in current layer
		else
		{
			int prevLayer = layer - 1;
			int prevLayerLength = prevLayer * 6;
			int currentLevelIndexTracker = currentLayerStartIndex;
			//loop on previous layer, identify corner and simple nodes and connect accorndingly
			for(int i = 0; i < prevLayerLength; i++)
			{
				Node prevNode = combArray[prevlayerStartIndex + i];
				Node currNode = combArray[currentLevelIndexTracker++];
				connectNodes(prevNode, currNode);
				if(i != 0 && i % prevLayer == 0) //corner node, except the first node
				{
					currNode = combArray[currentLevelIndexTracker++];
					connectNodes(prevNode, currNode);
				}
				currNode = combArray[currentLevelIndexTracker];
				connectNodes(prevNode, currNode);
				
			}
			//First node of prev layer(also a corner node) should also be connected to last node of 
			// current layer
			Node prevFirstNode = combArray[prevlayerStartIndex];
			Node currLastNode = combArray[currentLevelIndexTracker];
			connectNodes(prevFirstNode, currLastNode);
		}
	}
	
	private static void connectNodes(Node n1, Node n2)
	{
		n1.addAdjacentNode(n2);
		n2.addAdjacentNode(n1);
	}
	
	private void PopulateCharIntegerMap(Character c, Integer i)
	{
		if(!charNodeMap.containsKey(c))
			charNodeMap.put(c, new ArrayList<Integer>());	
		charNodeMap.get(c).add(i);
	}
	
	private void initializeComb(BufferedReader br) throws IOException
	{
		//First read, the center most node initialization
		String prevLine = br.readLine();
		if(prevLine == null || prevLine.length() != 1)
			throw new RuntimeException("Invalid File content - center most node should be representd by only one character");
		combArray[0] = new Node(0, 0, prevLine.charAt(0));
		PopulateCharIntegerMap(prevLine.charAt(0), 0);
		
		//Initialize all other layers
		int layer = 1;
		int prevlayerIndexStart = 0;
		int currentLayerIndexStart = 1;
		
		while(layer < layers)
		{
			String currentLayer = br.readLine();	
			
			if(currentLayer == null)
				throw new RuntimeException("Invalid File content - not enough lines");
			if(currentLayer.length() != layer * 6)
				throw new RuntimeException("Invalid File content - incorrect number of characters in layer " + (layer+ 1));
			//Create nodes in this layer
			addLayer(currentLayer, layer, currentLayerIndexStart);
			//Connect nodes in layers - a. among themselves and b. with previous layer
			connectNodesInLayer(prevlayerIndexStart, currentLayerIndexStart, layer);
			prevlayerIndexStart = currentLayerIndexStart;
			currentLayerIndexStart = currentLayerIndexStart + layer * 6;
			layer++;
		}
	}
}
