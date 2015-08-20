package com.concurrency.executor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class extends the ThreadPoolExecutor class to implement a customized executor.
 * It overrides the shutdown(), shutdownNow(), beforeExecute() and afterExecute() to
 * show statistics about the tasks executed by the Executor
 * 
 */
public class MyExecutor extends ThreadPoolExecutor {

	/**
	 * A HashMap to store the start date of the tasks executed by the executor. When 
	 * a task finish, it calculates the difference between the start date and the end date
	 * to show the duration of the task
	 */
	private ConcurrentHashMap<String, Date> startTimes;
	
	/**
	 * Constructor of the executor. Call the parent constructor and initializes the HashMap
	 * @param corePoolSize Number of threads to keep in the pool
	 * @param maximumPoolSize Maximum number of threads in the pool
	 * @param keepAliveTime Maximum time that threads can be idle
	 * @param unit Unit of time of the keepAliveTime parameter
	 * @param workQueue Queue where the submited tasks will be stored
	 */
	public MyExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes=new ConcurrentHashMap<>();
	}

	/**
	 * This method is called to finish the execution of the Executor. We write statistics
	 * about the tasks executed in it
	 */
	@Override
	public void shutdown() {
		System.out.printf("MyExecutor: Going to shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n",getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n",getActiveCount());
		System.out.printf("MyExecutor: Pending tasks: %d\n",getQueue().size());
		super.shutdown();
	}

	/**
	 * This method is called to finish the execution of the Executor immediately. We write statistics
	 * about the tasks executed in it
	 */
	@Override
	public List<Runnable> shutdownNow() {
		System.out.printf("MyExecutor: Going to immediately shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n",getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n",getActiveCount());
		System.out.printf("MyExecutor: Pending tasks: %d\n",getQueue().size());
		return super.shutdownNow();
	}

	/**
	 * This method is executed before the execution of a task. We store the start date in the HashMap
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.printf("MyExecutor: A task is beginning: %s : %s\n",t.getName(),r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}

	/**
	 * This method is executed after the execution of a task. We calculate the execution time of the task
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Future<?> result=(Future<?>)r;
		try {
			System.out.printf("*********************************\n");
			System.out.printf("MyExecutor: A task is finishing.\n");
			System.out.printf("MyExecutor: Result: %s\n",result.get());
			Date startDate=startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate=new Date();
			long diff=finishDate.getTime()-startDate.getTime();
			System.out.printf("MyExecutor: Duration: %d\n",diff);
			System.out.printf("*********************************\n");
		} catch (InterruptedException  | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
