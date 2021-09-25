package treeProblems;

public class deleteFromBST {

    binaryTreeNode DeleteNode(binaryTreeNode root, int value){
        binaryTreeNode curr = root;
        binaryTreeNode parent = null;

        // 1. Search for the node with id 'value'
        while(curr != null && curr.value != value)
        {
            parent = curr;
            if(curr.value > value)
                curr = curr.left;
            else
                curr = curr.right;
        }
        //case 1: Search didn't find any node
        if(curr == null)
            return null;
        // case 2: when either child is null
        if(curr.left == null || curr.right == null)
        {
            if(parent == null)
            {
                if(curr.left == null)
                    return curr.right;
                return curr.left;
            }
            else
            {
                if(parent.left == curr)
                    parent.left = curr.left == null ? curr.right : curr.left;
                else
                    parent.right = curr.left == null ? curr.right : curr.left;
                return root;
            }
        }

        //case 3: Find minimum in right subtree
        binaryTreeNode parent2 = curr;
        binaryTreeNode curr2 = curr.right;
        while(curr.left != null){
            parent2 = curr;
            curr2 = curr2.left;
        }
        curr.value = curr2.value;
        if(parent2.left == curr2)
            parent2.left = curr2.left == null ? curr2.right : curr2.left;
        else
            parent2.right = curr2.left == null ? curr2.right : curr2.left;
        return root;
    }

    public static void main(String[] args){
        System.out.println("Delete from BST");
    }
}
