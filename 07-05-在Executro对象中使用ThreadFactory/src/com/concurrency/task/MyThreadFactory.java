package com.concurrency.task;

import java.util.concurrent.ThreadFactory;

/**
 * Factory to create our kind of threads. Implement the
 * ThreadFactory interface.  
 *
 */
public class MyThreadFactory implements ThreadFactory {

	/**
	 * Attribute to store the number of threads created by the Factory
	 */
	private int counter;
	
	/**
	 * Prefix to use in the name of the threads created by the Factory
	 */
	private String prefix;
	
	/**
	 * Constructor of the class. Initializes its attributes
	 * @param prefix Prefix to use in the name of the threads
	 */
	public MyThreadFactory (String prefix) {
		this.prefix=prefix;
		counter=1;
	}
	
	/**
	 * Method that create a new MyThread object to execute the Runnable
	 * object that receives as parameter 
	 */
	@Override
	public Thread newThread(Runnable r) {
		MyThread myThread=new MyThread(r,prefix+"-"+counter);
		counter++;
		return myThread;
	}
}
