package treeProblems.inOrderIterator;
import java.util.Stack;

import treeProblems.tree.BinaryTreeNode;

public class InorderIterator 
{
	private BinaryTreeNode tracker;
	private BinaryTreeNode next;
	private Stack<BinaryTreeNode> stack;
	
	public InorderIterator(BinaryTreeNode root)
	{
		tracker = root;
		stack = new Stack<BinaryTreeNode>();
		setNext();
	}
	
	public BinaryTreeNode next()
	{
		if(!hasNext())
			throw new RuntimeException("No elements to iterate : Iterator nullified");
		BinaryTreeNode n = next;
		setNext();
		return n;
	}
	
	public boolean hasNext()
	{
		return null != next;
	}
	
	private void setNext()
	{
		next = null;
		while(null != tracker || !stack.isEmpty())
		{
			if(null != tracker)
			{
				stack.push(tracker);
				tracker = tracker.left;
			}
			else
			{
				next = stack.pop();
				tracker = next.right;
				return;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		BinaryTreeNode root = BinaryTreeNode.createBinaryTree(new Integer[] {1, 2, null, null, 3, null, null });
		InorderIterator ioItr = new InorderIterator(root);
		while(ioItr.hasNext())
		{
			BinaryTreeNode next = ioItr.next();
			System.out.print(next.value + "\t");
		}
		
		System.out.println();
		System.out.println("SUCCESS");
	}

}
