package treeProblems;
class resultCount
{
    public int nodesCounted;
    public resultCount(){
        nodesCounted = 0;
    }
}

public class kthSmallestElement {
    public static  binaryTreeNode kthSmallest(binaryTreeNode root, resultCount counter, int K){
        if(root == null)
            return null;
        binaryTreeNode leftResult = kthSmallest(root.left, counter, K);
        if(leftResult != null)
            return leftResult;
        counter.nodesCounted++;
        if(counter.nodesCounted == K)
            return root;
        return kthSmallest(root.right, counter, K);
    }

    public static binaryTreeNode kthSmallestDriver(binaryTreeNode root, int K){
        return kthSmallest(root, new resultCount(), K);
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
        System.out.println(kthSmallestDriver(root, 4).value);
    }
}
