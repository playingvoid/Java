package ThreadSafeQueue;

public class Consumer1 implements Runnable 
{
	private static final int DELAY = 100;
	private ThreadSafeQueue1<String> queue;
	private int count;

	public Consumer1(ThreadSafeQueue1<String> queue, int count) 
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
				System.out.println("consumed 1 : " + queue.popFront());
				Thread.sleep(DELAY);
			}
		} 
		catch(InterruptedException e) 
		{
		}
	}
}