package treeProblems;

public class leastCommonAncestorBST {

    public static binaryTreeNode findLCABST(binaryTreeNode node, int val1, int val2){
        if(node == null)
            return null;
        binaryTreeNode curr = node;

        if(val1 > val2){
            int temp = val1;
            val1 = val2;
            val2 = temp;
        }

        while(curr != null){
            if(val2 < curr.value)
                curr = curr.left;
            else if(val1 > curr.value)
                curr = curr.right;
            else
                return curr;
        }
        return null;

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
        System.out.println(findLCABST(root, 130,120));
    }
}
