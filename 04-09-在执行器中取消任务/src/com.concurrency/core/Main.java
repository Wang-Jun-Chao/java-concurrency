package com.concurrency.core;

import com.concurrency.task.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个执行器
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        // 创建一个任务
        Task task = new Task();

        System.out.printf("Main: Executing the Task\n");

        // 把任务发送到执行器
        Future<String> result = executor.submit(task);

        // 休眠两秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 取消一个任务完成他的执行
        System.out.printf("Main: Cancelling the Task\n");
        result.cancel(true);

        // 验证任务是否被取消
        System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
        System.out.printf("Main: Done: %s\n", result.isDone());

        // 关闭执行器
        executor.shutdown();
        System.out.printf("Main: The executor has finished\n");
    }
}
