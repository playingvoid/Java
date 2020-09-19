package treeProblems.tree;

public class BinaryTreeNode 
{
	public int value;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	private static class IntWrapper
	{
		public Integer value = 0;
	}
	
	public BinaryTreeNode(int value)
	{
		this.value = value;
		left = null;
		right = null;
	}
	
	@Override
	public String toString()
	{
		return Integer.toString(this.value);
	}
	
	/*
	 * Recursive pre-order traversal for a binary tree
	 */
	public static void preorderTraversalRecursive(BinaryTreeNode root)
	{
		if(root == null)
			return;
		System.out.print(root.value + "\t");
		preorderTraversalRecursive(root.left);
		preorderTraversalRecursive(root.right);
	}
	
	/*
	 * Recursive in-order traversal for a binary tree
	 */
	public static void inorderTraversalRecursive(BinaryTreeNode root)
	{
		if(root == null)
			return;
		inorderTraversalRecursive(root.left);
		System.out.print(root.value + "\t");
		inorderTraversalRecursive(root.right);
	}
	
	/*
	 * Recursive post-order traversal for a binary tree
	 */
	public static void postorderTraversalRecursive(BinaryTreeNode root)
	{
		if(root == null)
			return;
		postorderTraversalRecursive(root.left);
		postorderTraversalRecursive(root.right);
		System.out.print(root.value + "\t");
	}
	
	/*
	 * Expected to accept input as given by pre-order traversal, each leaf node should be marked as null.
	 * check the serialize/de-serialize example at: 
	 * http://articles.leetcode.com/2010/09/serializationdeserialization-of-binary.html
	 */
	public static BinaryTreeNode createBinaryTree(Integer[] array)
	{
		if(null == array) return null;
		return createBinaryTree(array, new IntWrapper());
	}
	
	public static BinaryTreeNode createBinaryTree2(Integer[] array)
	{
		if(null == array) return null;
		return createBinaryTree2(array, new IntWrapper());
	}
	
	private static BinaryTreeNode createBinaryTree(Integer[] array, IntWrapper index)
	{
		if(index.value >= array.length)
			return null;
		if(array[index.value] == null)
			return null;
		BinaryTreeNode root = new BinaryTreeNode(array[index.value]);
		index.value++;
		root.left = createBinaryTree(array, index);
		index.value++;
		root.right = createBinaryTree(array, index);
		return root;
	}
	
	private static BinaryTreeNode createBinaryTree2(Integer[] array, IntWrapper index)
	{
		int i = index.value;
		index.value++;
		if(array[i] == null)
			return null;
		BinaryTreeNode root = new BinaryTreeNode(array[i]);
		root.left = createBinaryTree2(array, index);
		root.right = createBinaryTree2(array, index);
		return root;
	}
}
