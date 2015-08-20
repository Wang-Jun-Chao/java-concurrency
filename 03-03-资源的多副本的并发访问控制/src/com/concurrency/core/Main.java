package com.concurrency.core;

import com.concurrency.task.Job;
import com.concurrency.task.PrintQueue;

public class Main {
    public static void main(String[] args) {
        // 创建一个打印对队对象
        PrintQueue printQueue = new PrintQueue();

        // 创建12个线程，运行作业任务，这些任务都向同一个打印队列对象发出打印请求
        Thread thread[] = new Thread[12];
        for (int i = 0; i < 12; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // 启动12个线程
        for (int i = 0; i < 12; i++) {
            thread[i].start();
        }
    }
}
