import java.util.Stack;

public class postorderIterator 
{
	private BinaryTreeNode tracker;
	private BinaryTreeNode next;
	private Stack<BinaryTreeNode> stack;
	
	public postorderIterator(BinaryTreeNode root)
	{
		tracker = root;
		stack = new Stack<BinaryTreeNode>();
		setNext();
	}
	
	public BinaryTreeNode next()
	{
		if(!hasNext())
			throw new RuntimeException("No elements to iterate : Iterator nullified");
		BinaryTreeNode n = next;
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
	
	public static void main(String[] args) 
	{
		BinaryTreeNode root = BinaryTreeNode.createBinaryTree(new Integer[] {1, 2, null, null, 3, null, null });
		postorderIterator ioItr = new postorderIterator(root);
		while(ioItr.hasNext())
		{
			BinaryTreeNode next = ioItr.next();
			System.out.print(next.value + "\t");
		}
		
		System.out.println();
		System.out.println("SUCCESS");
	}
}
