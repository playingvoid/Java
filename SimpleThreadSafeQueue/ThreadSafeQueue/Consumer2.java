package ThreadSafeQueue;

public class Consumer2 implements Runnable 
{
	private static final int DELAY = 10;
	private ThreadSafeQueue2<String> queue;
	private int count;

	public Consumer2(ThreadSafeQueue2<String> queue, int count) 
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
				System.out.println("consumed 2 : " + queue.popFront());
				Thread.sleep(DELAY);
			}
		} 
		catch(InterruptedException e) 
		{
		}
	}
}