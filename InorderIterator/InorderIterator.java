
public class InorderIterator 
{

	public static void main(String[] args) 
	{
		
		BinaryTreeNode root = BinaryTreeNode.createBinaryTree(new Integer[] {1, 2, null, null, 3, null, null });
		BinaryTreeNode.inorderTraversalRecursive(root);
		System.out.println();
		BinaryTreeNode.preorderTraversalRecursive(root);
		System.out.println();
		BinaryTreeNode.postorderTraversalRecursive(root);
	}

}
