package ThreadSafeQueue;

// Use of Synchronized methods (pushback and popFront)
public class ThreadSafeQueue1<T>  
{
	private Node front, back;
	private int size = 0;
	private static final int MAX_QUEUE_SIZE = 100;

	private class Node 
	{
		T item;
		Node next;
	}

	public synchronized void pushBack(final T item) throws InterruptedException 
	{ 
		while (size == MAX_QUEUE_SIZE) 
		{  
			wait();
		}

		final Node oldBack = back; 
		back = new Node();
		back.item = item;
		back.next = null;

		if (size == 0)  
			front = back;
		else 
			oldBack.next = back;
		
		size++; 
		notifyAll();
	}

	public synchronized T popFront() throws InterruptedException 
	{
		while (size == 0) 
		{ 
			wait();
		}

		final T item = front.item;
		front = front.next;

		size--; 
		if (size == 0) 	
			back = null;
		
		notifyAll();
		return item;
	}
}
