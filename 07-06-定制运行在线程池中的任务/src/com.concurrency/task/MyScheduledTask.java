package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * This class implements an scheduled task to be execute in a scheduled thread pool executor. It's
 * a parameterized class where V is the type of data that will be returned by the task. 
 * 
 * An scheduled thread pool executor can execute two kinds of tasks:
 * 		Delayed Tasks: This kind of tasks are executed once after a period of time.
 * 		Periodic Tasks: This kind of tasks are executed from time to time
 * @param <V> Type of data that will be returned by the task
 * 
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {

	/**
	 * Attribute to store the task that will be used to create a MyScheduledTask
	 */
	private RunnableScheduledFuture<V> task;
	
	/**
	 * ScheduledThreadPoolExecutor that is going to execute the task
	 */
	private ScheduledThreadPoolExecutor executor;
	
	/**
	 * Period of time between two executions of the task
	 */
	private long period;
	
	/**
	 * Date when will begin the next execution of the task
	 */
	private long startDate;
	
	/**
	 * Constructor of the class. It initializes the attributes of the class
	 * @param runnable Runnable submitted to be executed by the task
	 * @param result Result that will be returned by the task
	 * @param task Task that will execute the Runnable object
	 * @param executor Executor that is going to execute the task
	 */
	public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
		super(runnable, result);
		this.task=task;
		this.executor=executor;
	}

	/**
	 * Method that returns the reminder for the next execution of the task. If is 
	 * a delayed task, returns the delay of the original task. Else, return the difference
	 * between the startDate attribute and the actual date.
	 * @param unit TimeUnit to return the result
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		if (!isPeriodic()) {
			return task.getDelay(unit);
		} else {
			if (startDate==0){
				return task.getDelay(unit);
			} else {
				Date now=new Date();
				long delay=startDate-now.getTime();
				return unit.convert(delay, TimeUnit.MILLISECONDS);
			}
		}
	}

	/**
	 * Method to compare two tasks. It calls the compareTo() method of the original task
	 */
	@Override
	public int compareTo(Delayed o) {
		return task.compareTo(o);
	}

	/**
	 * Method that returns if the task is periodic or not. It calls the isPeriodic() method
	 * of the original task
	 */
	@Override
	public boolean isPeriodic() {
		return task.isPeriodic();
	}

	
	/**
	 * Method that executes the task. If it's a periodic task, it updates the 
	 * start date of the task and store in the queue of the executor the task to
	 * be executed again
	 */
	@Override
	public void run() {
		if (isPeriodic() && (!executor.isShutdown())) {
			Date now=new Date();
			startDate=now.getTime()+period;
			executor.getQueue().add(this);
		}
		System.out.printf("Pre-MyScheduledTask: %s\n",new Date());
		System.out.printf("MyScheduledTask: Is Periodic: %s\n",isPeriodic());
		super.runAndReset();
		System.out.printf("Post-MyScheduledTask: %s\n",new Date());
	}

	/**
	 * Method that establish the period of the task for periodic tasks
	 * @param period
	 */
	public void setPeriod(long period) {
		this.period=period;
	}
}
