package ThreadSafeQueue;

import java.util.Date;

public class Producer1 implements Runnable 
{
	private static final int DELAY = 10;
	private ThreadSafeQueue1<String> queue;
	private int count;

	public Producer1(ThreadSafeQueue1<String> queue, int count) 
	{
		this.queue = queue;
		this.count = count;
	}

	public void run() 
	{
		try 
		{
			for (int i = 0; i < count; ++i) 
			{
				String d = new Date().toString();
				System.out.println("Produced 1: " + d);
				queue.pushBack(d);
				Thread.sleep(DELAY);
			}
		} 
		catch(InterruptedException e) 
		{
		}
	}
}
