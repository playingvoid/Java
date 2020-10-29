package treeProblems;
import java.util.*;

public class rootToLeafPath {
    public static void printAllLeafPath(binaryTreeNode root, List<Integer> pathTillNow){
        if(root == null)
            return;
        pathTillNow.add(root.value);
        printAllLeafPath(root.left, pathTillNow);
        printAllLeafPath(root.right, pathTillNow);
        if(root.left == null && root.right == null){
            System.out.println(pathTillNow);
        }
        pathTillNow.remove(pathTillNow.size()-1);
    }

    public static void test1(){
        binaryTreeNode root = new binaryTreeNode(10);
        binaryTreeNode rootL = new binaryTreeNode(5);
        binaryTreeNode rootR = new binaryTreeNode(15);
        root.left = rootL;
        root.right = rootR;
        binaryTreeNode rootLR = new binaryTreeNode(8);
        rootL.right = rootLR;
        printAllLeafPath(root, new ArrayList<>());
    }
    public static void main(String[] args){
        test1();
    }
}
