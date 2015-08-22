package ThreadSafeQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Another approach one may take to make use of Lock and Condition 
// (instead of synchronized, wait() and notifiyAll()) interfaces as follows
public class ThreadSafeQueue2<T>  
{
	private Node front, back;
	private int size = 0;
	private static final int MAX_QUEUE_SIZE = 100;
	final Lock lock = new ReentrantLock();
	final Condition notFull  = lock.newCondition(); 
	final Condition notEmpty = lock.newCondition(); 

	private class Node 
	{
		T item;
		Node next;
	}

	public void pushBack(final T item) throws InterruptedException 
	{ 
		lock.lock();
		try
		{
			while (size == MAX_QUEUE_SIZE) 
			{  
				notFull.await();
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
			notEmpty.signal();
		}
		finally 
		{
			lock.unlock();
		}
	}

	public T popFront() throws InterruptedException 
	{
		lock.lock();
		try
		{
			while (size == 0) 
			{ 
				System.out.println("Waiting");
				notEmpty.await();
			}

			final T item = front.item;
			front = front.next;

			size--; 
			if (size == 0) 	
				back = null;
			
			notFull.signal();
			return item;
		}
		finally 
		{
			lock.unlock();
		}
	}
}

