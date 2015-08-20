package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * Runnable object to check the MyScheduledTask and MyScheduledThreadPoolExecutor classes.
 *
 */
public class Task implements Runnable {

	/**
	 * Main method of the task. Writes a message, sleeps the current thread for two seconds and
	 * writes another message
	 */
	@Override
	public void run() {
		System.out.printf("Task: Begin.\n");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task: End.\n");
	}

}
