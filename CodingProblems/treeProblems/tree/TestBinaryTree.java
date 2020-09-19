package treeProblems.tree;

public class TestBinaryTree {
	
	public static void main(String[] args){
		BinaryTreeNode btNode = BinaryTreeNode.createBinaryTree2(new Integer[]{1, null, null});
		BinaryTreeNode.preorderTraversalRecursive(btNode);
		System.out.println();
		btNode = BinaryTreeNode.createBinaryTree2(new Integer[]{1, 2, 5,null,null,6, null,null,3,null,4,null,null});	
		BinaryTreeNode.preorderTraversalRecursive(btNode);
		System.out.println();
		btNode = BinaryTreeNode.createBinaryTree(new Integer[]{1, 2, 5,null,null,6, null,null,3,null,4,null,null});
		BinaryTreeNode.preorderTraversalRecursive(btNode);
		System.out.println();
		BinaryTreeNode.inorderTraversalRecursive(btNode);
	}

}
