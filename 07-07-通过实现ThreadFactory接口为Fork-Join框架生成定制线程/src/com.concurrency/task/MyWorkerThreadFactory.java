package com.concurrency.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;

/**
 * Factory to be used by the Fork/Join framework to create the worker threads. Implements
 * the ForkJoinWorkerThreadFactory interface
 *
 */
public class MyWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

	/**
	 * Method that creates a worker thread for the Fork/Join framework
	 * @param pool ForkJoinPool where the thread will be executed
	 * @return a MyWorkerThread thread
	 */
	@Override
	public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
		return new MyWorkerThread(pool);
	}

}
