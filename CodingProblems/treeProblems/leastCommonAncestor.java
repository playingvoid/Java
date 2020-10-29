package treeProblems;

/*
PROBLEM: EPI 10.3
*/

class lcaResult {
    public binaryTreeNode lca;
    public int count;
    public lcaResult(){
        lca = null;
        count = 0;
    }
    public lcaResult(int count){
        lca = null;
        this.count = count;
    }

    public lcaResult(binaryTreeNode lca, int count){
        this.lca = lca;
        this.count = count;
    }
}

public class leastCommonAncestor {

    public static lcaResult findLCAHelper(binaryTreeNode root, int val1, int val2){
        if(root == null){
            return new lcaResult();
        }

        lcaResult leftResult = findLCAHelper(root.left, val1, val2);
        if(leftResult.count == 2)
            return leftResult;

        lcaResult rightResult = findLCAHelper(root.right, val1, val2);
        if(rightResult.count == 2)
            return  rightResult;

        int count = leftResult.count + rightResult.count;
        if(root.value == val1)
            count++;
        if(root.value == val2)
            count++;
        if(count == 2)
            return new lcaResult(root, 2);
        return new lcaResult(count);
    }

    public static binaryTreeNode findLCA(binaryTreeNode root, int val1, int val2){
        return findLCAHelper(root, val1, val2).lca;
    }

    public static void main(String[] args){
        binaryTreeNode rootA = new binaryTreeNode(21);
        binaryTreeNode rootB = new binaryTreeNode(10);
        rootA.right = rootB;

        binaryTreeNode rootL = new binaryTreeNode(5);
        binaryTreeNode rootR = new binaryTreeNode(15);
        rootB.left = rootL;
        rootB.right = rootR;
        binaryTreeNode rootLR = new binaryTreeNode(8);
        binaryTreeNode rootLL = new binaryTreeNode(3);
        rootL.left = rootLL;
        rootL.right = rootLR;
        System.out.println(findLCA(rootA, 3,15));
    }
}
