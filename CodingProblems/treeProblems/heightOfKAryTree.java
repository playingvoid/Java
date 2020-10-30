package treeProblems;

public class heightOfKAryTree {
    public static int heightOfKAryTree(binaryTreeNode root){
        if(root == null)
            return 0;
        int leftHeight = heightOfKAryTree(root.left);
        int rightHeight = heightOfKAryTree(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int heightOfKAryTreeDriver(binaryTreeNode root){
        if(root == null)
            return -1;
        return heightOfKAryTree(root) - 1;
    }

    public static void main(String[] args){
        binaryTreeNode root = new binaryTreeNode(100);
        binaryTreeNode rootR = new binaryTreeNode(150);
        root.right = rootR;

        binaryTreeNode rootRL = new binaryTreeNode(125);
        binaryTreeNode rootRR = new binaryTreeNode(175);
        rootR.left = rootRL;
        rootR.right = rootRR;

        binaryTreeNode rootRLR = new binaryTreeNode(130);
        binaryTreeNode rootRLL = new binaryTreeNode(120);
        rootRL.left = rootRLL;
        rootRL.right = rootRLR;
        binaryTreeNode rootRLRR = new binaryTreeNode(135);
        rootRLR.right = rootRLRR;
        System.out.println(heightOfKAryTreeDriver(root));
    }
}
