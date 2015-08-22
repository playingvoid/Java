package ThreadSafeQueue;

public class QueueTester 
{
	private static void testImplementation1()
	{
		ThreadSafeQueue1<String> queue = new ThreadSafeQueue1<String>();
		final int REPETITIONS = 50;

		Producer1 pr = new Producer1(queue, REPETITIONS);
		Consumer1 cr = new Consumer1(queue, REPETITIONS);

		Thread dt = new Thread(pr);
		Thread wt = new Thread(cr);

		dt.start();
		wt.start();
	}

	private static void testImplementation2()
	{
		ThreadSafeQueue2<String> queue = new ThreadSafeQueue2<String>();
		final int REPETITIONS = 50;

		Producer2 pr = new Producer2(queue, REPETITIONS);
		Consumer2 cr = new Consumer2(queue, REPETITIONS);

		Thread dt = new Thread(pr);
		Thread wt = new Thread(cr);

		dt.start();
		wt.start();	
	}

	public static void main(String[] args)
	{
		testImplementation1();

		testImplementation2();
	}
}