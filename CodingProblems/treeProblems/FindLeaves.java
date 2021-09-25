package treeProblems;
import java.awt.desktop.SystemEventListener;
import java.util.*;
public class FindLeaves {

    public static void main(String[] args){
        binaryTreeNode btNode1 = new binaryTreeNode(1);
        binaryTreeNode btNode2 = new binaryTreeNode(2);
        binaryTreeNode btNode3 = new binaryTreeNode(3);
        binaryTreeNode btNode4 = new binaryTreeNode(4);
        binaryTreeNode btNode5 = new binaryTreeNode(5);

        //binaryTreeNode btNode6 = btNode1;

        btNode1.left = btNode2; btNode1.right = btNode3;
        btNode2.left = btNode4; btNode2.right = btNode5;

        List<List<Integer>> output = findLeaves(btNode1);

        System.out.println(output);


    }

    public static List<List<Integer>> findLeaves(binaryTreeNode root) {
        if(root ==  null)
            return null;

        List<List<Integer>> output = new ArrayList<>();
        while(root != null)
        {
            List<Integer> tempOutput = new ArrayList<Integer>();
            System.out.println("Calling findLeavesHelper for " + root.value);
            root = findLeavesHelper(root, null, tempOutput);
            output.add(tempOutput);
            //System.out.println("one loop over ....  " + root.val);
            //if(isLeaf(root)){
            //    System.out.println("Calling findLeavesHelper for.... is leaf is true " + root.value);
            //    break;
            //}

        }

        return output;
    }

    static  binaryTreeNode findLeavesHelper(binaryTreeNode root, binaryTreeNode parent, List<Integer> output){


        if(root == null)
            return null;

        System.out.println("findLeavesHelper for root " + root.value
                + "  parent: " + ((parent!= null) ? parent.value : "null"));

        if(isLeaf(root)){
            System.out.println("IsLeaf is for root " + root.value);
            output.add(root.value);
            if(parent != null){
                if(parent.left == root)
                {System.out.println("Setting left as null for parent " + parent.value);
                    parent.left = null;
                }
                else if(parent.right == root) {
                    System.out.println("Setting right as null for parent " + parent.value);
                    parent.right = null;
                }
                return root;
            }
            return null;
        }

        findLeavesHelper(root.left, root, output);
        findLeavesHelper(root.right, root, output);
        return root;
    }

    static boolean  isLeaf(binaryTreeNode root){
        return root.left == null && root.right == null;
    }
}
