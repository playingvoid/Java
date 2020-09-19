package brproblems;

import java.util.*;

class BinaryTreeNode {
	public int value;
	public int nodeId;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	public BinaryTreeNode(int nodeId, int value){
		this.value = value;
		this.nodeId = nodeId;
		this.left = null;
		this.right = null;
	}
	
	/*
	 * Deserialized binary tree from the given serialized tree
	 */
	public static BinaryTreeNode deSerialize(String serialized){
		if(serialized == null || serialized.length() == 0 || serialized == "#")
			return null;
		
		String[] splittedSerialized = serialized.split(" ");
		int maxIndex = splittedSerialized.length - 1;
		BinaryTreeNode root = new BinaryTreeNode(0, Integer.parseInt(splittedSerialized[0]));
		
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		int index = 1;
		while(!queue.isEmpty()){
			BinaryTreeNode btNode = queue.poll();
			if(btNode == null)
				continue;
			if(index > maxIndex || splittedSerialized[index].equals("#")){
				btNode.left = null;
			} else {
				btNode.left = new BinaryTreeNode(index, Integer.parseInt(splittedSerialized[index]));
			}
			queue.add(btNode.left);
			index++;
			if(index > maxIndex || splittedSerialized[index].equals("#")){
				btNode.right = null;
			} else {
				btNode.right = new BinaryTreeNode(index, Integer.parseInt(splittedSerialized[index]));
			}
			queue.add(btNode.right);
			index++;
		}
		
		return root;
	}
}

public class PortfolioManager {
	
	/*
	 * Class created for memoization purpose, acts as a key to 
	 * already calculated values
	 */
	private static class MaxValue{
		int nodeId;
		boolean selected;
		
		public MaxValue(int nodeId, boolean selected){
			this.nodeId = nodeId;
			this.selected = selected;
		}
		
		@Override
		public boolean equals(Object other){
			if(other == null)
				return false;
			if (other == this) 
				return true;
	        
			if (!(other instanceof MaxValue)) {
	            return false;
	        }

			MaxValue otherMaxValue = (MaxValue) other;

	        return otherMaxValue.selected == this.selected 
	        		&& otherMaxValue.nodeId == this.nodeId;
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(this.nodeId, this.selected);
		}
		
	}
	///Memory for already calculated value #Memoization
	static HashMap<MaxValue, Long> maxValueMap = new HashMap<>();
	
	static long findMax(BinaryTreeNode root, boolean selected){
		if(root == null)
			return 0;
		
		MaxValue maxValueKey = new MaxValue(root.nodeId, selected);
		Long total = maxValueMap.getOrDefault(maxValueKey, null);
		if(total != null)
			return total.longValue();

		if(selected){
			Long totalSelected = root.value + findMax(root.left, false) + findMax(root.right, false);
			maxValueMap.put(maxValueKey, totalSelected);
			return totalSelected;
		}
		
		Long leftMax = Math.max(findMax(root.left, false), findMax(root.left, true));
		Long rightMax = Math.max(findMax(root.right, false), findMax(root.right, true));
		maxValueMap.put(maxValueKey, leftMax + rightMax);
		return leftMax + rightMax;
	}
	
	static long findMax(int n, String tree){
		maxValueMap.clear();
		BinaryTreeNode protfolioTree = BinaryTreeNode.deSerialize(tree);
		return Math.max(findMax(protfolioTree, true), findMax(protfolioTree, false));
	}
	
	public static void main(String[] args){
		System.out.println(findMax(6, "3 4 5 1 3 # 1"));
		System.out.println(findMax(6, "3 2 3 # 3 # 1"));
	}
}
