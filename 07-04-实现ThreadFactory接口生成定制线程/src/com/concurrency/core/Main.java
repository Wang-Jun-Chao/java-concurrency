package com.concurrency.core;

import com.concurrency.task.MyTask;
import com.concurrency.task.MyThreadFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        // 创建一个线程工厂
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");

        // 创建一个任务
        MyTask task = new MyTask();

        // 使用自定义的线程工厂创建一个新的线程
        Thread thread = myFactory.newThread(task);

        // 开始执行线程
        thread.start();

        // 等待线程执行结束
        thread.join();

        // 输出线程信息
        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example.\n");
    }
}
