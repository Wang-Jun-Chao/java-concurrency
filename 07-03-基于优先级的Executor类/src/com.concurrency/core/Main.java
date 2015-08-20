package com.concurrency.core;

import com.concurrency.task.MyPriorityTask;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main method of the class. It creates an Executor with a PriorityQueue as working queue and then
 * sends various tasks with different priorities to check that they are executed in the correct order
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
		 * Create an executor with a PriorityBlockingQueue as the structure to store the tasks
		 */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());

		/*
		 * Send four task to the executor
		 */
        for (int i = 0; i < 4; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
        }
		
		/*
		 * sleep the thread during one second
		 */
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		/*
		 * Send four tasks to the executor
		 */
        for (int i = 4; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
        }
		
		/*
		 * Shutdown the executor
		 */
        executor.shutdown();
		
		/*
		 * Wait for the finalization of the executor
		 */
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		/*
		 * Write a message to the console indicating the end of the program
		 */
        System.out.printf("Main: End of the program.\n");
    }

}
