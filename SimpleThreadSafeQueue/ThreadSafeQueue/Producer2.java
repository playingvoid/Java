package ThreadSafeQueue;

import java.util.Date;

public class Producer2 implements Runnable 
{
	private static final int DELAY = 100;
	private ThreadSafeQueue2<String> queue;
	private int count;

	public Producer2(ThreadSafeQueue2<String> queue, int count) 
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
				String d = i + ":" + new Date().toString();
				System.out.println("Produced 2: " + d);
				queue.pushBack(d);
				Thread.sleep(DELAY);
			}
		} 
		catch(InterruptedException e) 
		{
		}
	}
}
