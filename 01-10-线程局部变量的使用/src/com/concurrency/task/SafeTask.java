package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {
    /**
     * 线程局部变量，其中的内容不能共享，线程被初始化时会创建其包含的变量
     */
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());
    }
}
