package com.concurrency.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * This class implements a custom thread for the Fork/Join framework. It extends the
 * ForkJoinWorkerThread that is the default implementation of the threads that executes
 * the tasks in the Fork/Join Framework. This custom thread counts the number of tasks
 * executed in it
 */
public class MyWorkerThread extends ForkJoinWorkerThread {

    /**
     * ThreadLocal attribute to store the number of tasks executed by each thread
     */
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

    /**
     * Constructor of the class. It calls the constructor of its parent class using the
     * super keyword
     *
     * @param pool ForkJoinPool where the thread will be executed
     */
    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    /**
     * This method is called when a worker thread of the Fork/Join framework begins its execution.
     * It initializes its task counter
     */
    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
        taskCounter.set(0);
    }

    /**
     * This method is called when a worker thread of the Fork/Join framework ends its execution.
     * It writes in the console the value of the taskCounter attribute.
     */
    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    /**
     * This method is called for each task to increment the task counter of the worker thread
     */
    public void addTask() {
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }

}
