package com.concurrency.core;

import com.concurrency.task.MyRecursiveTask;
import com.concurrency.task.MyWorkerThreadFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

		// 创建一个工厂线程工厂
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();

		// 创建一个Fork/Join池
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);

		// 初始化一个待计算的数组
        int array[] = new int[100000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }

		// 创建一个执行计算的任务对象
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);

		// 任务提交到fork/join池中
        pool.execute(task);

        // 待待任务执行束
        task.join();

		// 关闭Fork/Join池
        pool.shutdown();

		// 待待池中的所有任务运行结束
        pool.awaitTermination(1, TimeUnit.DAYS);

		// 输出任务执行的结果
        System.out.printf("Main: Result: %d\n", task.get());

		// 输出消息表示程序运行结束
        System.out.printf("Main: End of the program\n");
    }

}
