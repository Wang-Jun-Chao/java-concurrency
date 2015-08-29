package com.concurrency.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 自定义工作线程
 */
public class MyWorkerThread extends ForkJoinWorkerThread {

    // 每个线程所执行的任务数
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

    /**
     * 构造函数
     *
     * @param pool 分合池对象
     */
    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    /**
     * 当一个Fork/Join框架的线程开始执行任务的时候进行调用，它初始化任务计数器
     */
    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
        taskCounter.set(0);
    }

    /**
     * 当一个Fork/Join框架的线程结束执行任务的时候进行调用，输出线程执行的任务数
     */
    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    /**
     * 增加任务计数
     */
    public void addTask() {
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }
}
