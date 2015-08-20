package com.concurrency.core;

import com.concurrency.task.MyRecursiveTask;
import com.concurrency.task.MyWorkerThreadFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


/**
 * Main class of the example. It creates an array of 100000 elements, initializes all
 * the elements to the 1 value, creates a new ForkJoinPool with the new
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

		/*
         * Create a new MyWorkerThreadFactory
		 */
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
		
		/*
		 * Create new ForkJoinPool, passing as parameter the factory created before
		 */
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);
		
		/*
		 * Create and initializes the elements of the array
		 */
        int array[] = new int[100000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
		
		/*
		 * Create a new Task to sum the elements of the array
		 */
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
		
		/*
		 * Send the task to the ForkJoinPool 
		 */
        pool.execute(task);
		
		
		/*
		 * Wait for the finalization of the task
		 */
        task.join();
		
		/*
		 * Shutdown the pool
		 */
        pool.shutdown();
		
		/*
		 * Wait for the finalization of the pool
		 */
        pool.awaitTermination(1, TimeUnit.DAYS);
		
		/*
		 * Write the result of the task
		 */
        System.out.printf("Main: Result: %d\n", task.get());
		
		/*
		 * Write a message indicating the end of the program
		 */
        System.out.printf("Main: End of the program\n");
    }

}
