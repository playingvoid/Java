package concurrencyPractice;

import java.util.*;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;


public class ConcurrentMapVsSyncMap {
	public final static int THREAD_POOL_SIZE = 5;
	
	public static Map<String, Integer> simpleMap = null;
	public static Map<String, Integer> concurrentMap = null;
	public static Map<String, Integer> syncMap = null;
	
	static {
		simpleMap = new Hashtable<>();
		syncMap = Collections.synchronizedMap(new HashMap<String, Integer>());
		concurrentMap = new ConcurrentHashMap<>();	
	}
	
	public static void main(String[] args) throws InterruptedException {
		performanceTest(simpleMap);
		performanceTest(syncMap);
		performanceTest(concurrentMap);
	}
	
	public static void performanceTest(final Map<String, Integer> inputMap) throws InterruptedException {
		System.out.println("Test started for: " + inputMap.getClass());
		long averageTime = 0;
		for (int i = 0; i < 5; i++) {
			long startTime = System.nanoTime();
		
			ExecutorService threadServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
			for(int j = 0; j < THREAD_POOL_SIZE; j++){
				
				threadServer.execute(new Runnable(){
					@SuppressWarnings("unused")
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int j = 0; j < 500000; j++) {
							Integer randomNumber = (int) Math.ceil(Math.random() * 550000);
							// Retrieve value. We are not using it anywhere
							Integer crunchifyValue = inputMap.get(String.valueOf(randomNumber));
							// Put value
							inputMap.put(String.valueOf(randomNumber), randomNumber);	
						}
					}
					
				});
			}
		
			// Make sure executor stops
			threadServer.shutdown();
			// Blocks until all tasks have completed execution after a shutdown request
			threadServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			long entTime = System.nanoTime();
			long totalTime = (entTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
		}
		System.out.println("For " + inputMap.getClass() + " the average time is " + averageTime / 5 + " ms\n");
	}
	
}
