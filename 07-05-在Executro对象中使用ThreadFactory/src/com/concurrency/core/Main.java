package com.concurrency.core;

import com.concurrency.task.MyTask;
import com.concurrency.task.MyThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Main class of the example. Creates a Factory, an Executor using
 * that factory and submits a task to the executor
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		/*
		 * Create a new MyThreadFactory object
		 */
		MyThreadFactory threadFactory=new MyThreadFactory("MyThreadFactory");
		
		/*
		 * Create a new ThreadPoolExecutor and configure it for use the 
		 * MyThreadFactoryObject created before as the factory to create the threads
		 */
		ExecutorService executor=Executors.newCachedThreadPool(threadFactory);
		
		/*
		 * Create a new Task object
		 */
		MyTask task=new MyTask();
		
		/*
		 * Submit the task 
		 */
		executor.submit(task);
		
		/*
		 * Shutdown the executor
		 */
		executor.shutdown();
		
		/*
		 * Wait for the finalization of the executor
		 */
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		/*
		 * Write a message indicating the end of the program
		 */
		System.out.printf("Main: End of the program.\n");
		
		

	}

}
