package treeProblems;

import java.util.Stack;

public class postOrderIterator {
    private binaryTreeNode tracker;
    private binaryTreeNode next;
    private Stack<binaryTreeNode> stack;

    public postOrderIterator(binaryTreeNode root)
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
                if(null != tracker.right)
                    stack.push(tracker.right);
                stack.push(tracker);
                tracker = tracker.left;
            }
            else
            {
                next = stack.pop();
                if(!stack.isEmpty() && next.right == stack.peek())
                {
                    tracker = stack.pop();
                    stack.push(next);
                }
                else
                {
                    tracker = null;
                    return;
                }
            }
        }
    }

    // Based on http://n00tc0d3r.blogspot.com/2013/08/implement-iterator-for-binarytree-iii.html
    private void PostOrderAnother(binaryTreeNode root)
    {
        Stack<binaryTreeNode> stack = new Stack<binaryTreeNode>();
        binaryTreeNode current = root;

        while(current != null || !stack.isEmpty())
        {
            if(current != null)
            {
                stack.push(current);
                if(current.left != null)
                    current = current.left;
                else
                    current = current.right;
            }
            else
            {
                current = stack.pop();
                //visit(current);
                if(!stack.isEmpty())
                {
                    var top = stack.peek();
                    if(top.left == current)
                    {
                        current = top.left.right;
                    }
                    else
                        current = null;
                }
            }
        }
    }

    // Following is wrong code
    private void PostOrderTraversalUsingSingleStack(binaryTreeNode root)
    {
        Stack<binaryTreeNode> postOrderStack = new Stack<binaryTreeNode>();
        binaryTreeNode lastVisitedNode = null;
        binaryTreeNode curr = root;

        while(postOrderStack != null || curr != null)
        {
            if(curr != null)
            {
                postOrderStack.push(curr);
                if(curr.right != null)
                    postOrderStack.push(curr.right);
                curr = curr.left;
            }
            else
            {
                curr = postOrderStack.pop();
                if(curr.right == null || curr.right == lastVisitedNode)
                {
                    //visit(curr);
                    lastVisitedNode = curr;
                    curr = null;
                }
            }
        }
    }



    public static void main(String[] args)
    {
        binaryTreeNode root = binaryTreeNode.createBinaryTree(new Integer[] {1, 2, null, null, 3, null, null });
        postOrderIterator ioItr = new postOrderIterator(root);
        while(ioItr.hasNext())
        {
            binaryTreeNode next = ioItr.next();
            System.out.print(next.value + "\t");
        }

        System.out.println();
        System.out.println("SUCCESS");
    }
}
