package com.concurrency.task;

import com.concurrency.core.Main;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 打印队列类，使用信号量来控制打钱作业的访问
 */
public class PrintQueue {
    /**
     * 信号量，控制队列的访问
     */
    private final Semaphore semaphore;

    /**
     * 构造函数，初始化信号量
     */
    public PrintQueue() {
        semaphore = new Semaphore(1);
    }

    /**
     * 模拟文档打印的方法
     *
     * @param document 需要打印的对象
     */
    public void printJob(Object document) {
        try {
            // 请求信号量，如果已经被其它线程请求过，则当前请求的线程会休眠，直到获得这个信号量
            semaphore.acquire();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量，如果有其它的线程在请求这个信号量，JVN会选择其中的某一个程获取信号量，让其运行
            semaphore.release();
        }
    }
}
