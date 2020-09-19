package treeProblems.levelOrderSerializeDeserialize;

import java.util.*;
import treeProblems.tree.BinaryTreeNode;

public class LevelOrderSerializeDeserialize {
	
	public static String serialize(BinaryTreeNode root){
		if(root == null)
			return null;
		StringBuilder sb = new StringBuilder();
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			BinaryTreeNode btNode = queue.poll();	
			if(btNode != null){
				sb.append(btNode.value);
				queue.add(btNode.left);
				queue.add(btNode.right);
			} else {
				sb.append("#");
			}
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	
	public static BinaryTreeNode deSerialize(String serialized){
		if(serialized == null || serialized.length() == 0 || serialized == "#")
			return null;
		
		String[] splittedSerialized = serialized.split(" ");
		
		BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(splittedSerialized[0]));
		
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		int index = 1;
		while(!queue.isEmpty()){
			BinaryTreeNode btNode = queue.poll();
			if(btNode == null)
				continue;
			if(!splittedSerialized[index].equals("#"))
				btNode.left = new BinaryTreeNode(Integer.parseInt(splittedSerialized[index]));
			else 
				btNode.left = null;
			queue.add(btNode.left);
			index++;
			if(!splittedSerialized[index].equals("#"))
				btNode.right = new BinaryTreeNode(Integer.parseInt(splittedSerialized[index]));
			else 
				btNode.right = null;
			queue.add(btNode.right);
			index++;
		}
		
		return root;
	}
	
	public static void main(String[] args){
		System.out.println("This is serialize desrialize");
		BinaryTreeNode btNode = BinaryTreeNode.createBinaryTree(new Integer[]{
				1, 2, 5,null,null,6, null,null,3,null,4,null,null
				});
		String serialized = serialize(btNode);
		System.out.println(serialized);
		
		BinaryTreeNode btNode2 = deSerialize(serialized);
		serialized = serialize(btNode2);
		System.out.println(serialized);
	}
}
