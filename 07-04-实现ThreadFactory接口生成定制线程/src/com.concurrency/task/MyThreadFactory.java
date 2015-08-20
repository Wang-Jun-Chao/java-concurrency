package com.concurrency.task;

import java.util.concurrent.ThreadFactory;

/**
 * Factory to create MyThread objects
 *
 */
public class MyThreadFactory implements ThreadFactory {

	/**
	 * Attribute to store the number of threads created in this factory
	 */
	private int counter;
	
	/**
	 * String to create the name of the threads created with this factory
	 */
	private String prefix;
	
	/**
	 * Constructor of the class. Initialize its parameters
	 * @param prefix First part of the name of the threads created with this factory
	 */
	public MyThreadFactory (String prefix) {
		this.prefix=prefix;
		counter=1;
	}
	
	/**
	 * Method that creates a new MyThread thread
	 */
	@Override
	public Thread newThread(Runnable r) {
		MyThread myThread=new MyThread(r,prefix+"-"+counter);
		counter++;
		return myThread;
	}

}
