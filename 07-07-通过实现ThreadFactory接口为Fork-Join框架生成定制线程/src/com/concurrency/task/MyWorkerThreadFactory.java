package com.concurrency.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;

/**
 * 自定义Fork/Join工作线程工厂
 */
public class MyWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

    /**
     * 为Fork/Join框架创建一个工厂线程
     *
     * @param pool 线程将要被执行的线程池
     * @return 一个自定义的工作线程对象
     */
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkerThread(pool);
    }

}
