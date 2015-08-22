package com.concurrency.core;

import com.concurrency.task.Task;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 创建一个定时线程池执行器对象
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        // 使用ScheduledThreadPoolExecutor实例的schedule()方法，初始化和开始一些任务（5个任务）。
        System.out.printf("Main: Starting at: %s\n", new Date());
        for (int i = 0; i < 5; i++) {
            Task task = new Task("Task " + i);
            executor.schedule(task, i + 1, TimeUnit.SECONDS);
        }

        // 关闭线程执行器对象
        executor.shutdown();

        // 等待线程执行器的完成
        try {
            // 所有线程必须在24小时内完成，否则就终止未完成的线程
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出完成消息
        System.out.printf("Core: Ends at: %s\n", new Date());
    }
}
