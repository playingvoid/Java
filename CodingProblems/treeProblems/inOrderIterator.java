package treeProblems;

import java.util.Stack;

public class inOrderIterator {
    private binaryTreeNode tracker;
    private binaryTreeNode next;
    private Stack<binaryTreeNode> stack;

    public inOrderIterator(binaryTreeNode root)
    {
        tracker = root;
        stack = new Stack<binaryTreeNode>();
        setNext();
    }

    public binaryTreeNode next()
    {
        if(!hasNext())
            throw new RuntimeException("No elements to iterate : Iterator nullified");
        binaryTreeNode n = next;
        setNext();
        return n;
    }

    public boolean hasNext()
    {
        return null != next;
    }

    private void setNext()
    {
        next = null;
        while(null != tracker || !stack.isEmpty())
        {
            if(null != tracker)
            {
                stack.push(tracker);
                tracker = tracker.left;
            }
            else
            {
                next = stack.pop();
                tracker = next.right;
                return;
            }
        }
    }

    public static void main(String[] args)
    {
        binaryTreeNode root = binaryTreeNode.createBinaryTree(new Integer[] {1, 2, null, null, 3, null, null });
        inOrderIterator ioItr = new inOrderIterator(root);
        while(ioItr.hasNext())
        {
            binaryTreeNode next = ioItr.next();
            System.out.print(next.value + "\t");
        }

        System.out.println();
        System.out.println("SUCCESS");
    }

}
