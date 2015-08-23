package com.concurrency.core;

import com.concurrency.task.Task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 创建长度为100的整形数组
        int array[] = new int[100];
        // 创建处理数组的任务
        Task task = new Task(array, 0, 100);
        // 创建分合池对象去执行这个任务
        ForkJoinPool pool = new ForkJoinPool();

        // 执行任务
        pool.execute(task);

        // 关闭分合池
        pool.shutdown();

        // 等待任务的完成
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查是否抛出异常，如果抛出异常就输出信息
        if (task.isCompletedAbnormally()) {
            System.out.printf("Main: An exception has ocurred\n");
            System.out.printf("Main: %s\n", task.getException());
        }

        System.out.printf("Main: Result: %d", task.join());
    }
}
