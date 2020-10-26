package treeProblems;

public class testBinaryTree {

    public static void main(String[] args){
        binaryTreeNode btNode = binaryTreeNode.createBinaryTree2(new Integer[]{1, null, null});
        binaryTreeNode.preorderTraversalRecursive(btNode);
        System.out.println();
        btNode = binaryTreeNode.createBinaryTree2(new Integer[]{1, 2, 5,null,null,6, null,null,3,null,4,null,null});
        binaryTreeNode.preorderTraversalRecursive(btNode);
        System.out.println();
        btNode = binaryTreeNode.createBinaryTree(new Integer[]{1, 2, 5,null,null,6, null,null,3,null,4,null,null});
        binaryTreeNode.preorderTraversalRecursive(btNode);
        System.out.println();
        binaryTreeNode.inorderTraversalRecursive(btNode);
    }

}