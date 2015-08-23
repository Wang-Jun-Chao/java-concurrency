package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * This class implements a Task that uses the Lock
 *
 */
public class Task implements Runnable {
	
	/**
	 * Lock used by the task
	 */
	private MyLock lock;
	
	/**
	 * Name of the task
	 */
	private String name;
	
	/**
	 * Constructor of the class
	 * @param name Name of the task
	 * @param lock Lock used by the task
	 */
	public Task(String name, MyLock lock){
		this.lock=lock;
		this.name=name;
	}

	/**
	 * Main method of the task. It gets the lock, sleep the thread for two seconds
	 * and then release the lock.
	 */
	@Override
	public void run() {
		lock.lock();
		System.out.printf("Task: %s: Take the lock\n",name);
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.printf("Task: %s: Free the lock\n",name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
