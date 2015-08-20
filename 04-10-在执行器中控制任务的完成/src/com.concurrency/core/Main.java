package com.concurrency.core;

import com.concurrency.task.ExecutableTask;
import com.concurrency.task.ResultTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个执行器对象
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();

        // 创建5个任务
        ResultTask resultTasks[] = new ResultTask[5];
        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("Task " + i);
            resultTasks[i] = new ResultTask(executableTask);
            executor.submit(resultTasks[i]);
        }

        // 休眠5秒钟
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // 取消所有的任务，如果任务在取消之前已经完，取消操作对任务没有任命影响
        for (ResultTask resultTask : resultTasks) {
            resultTask.cancel(true);
        }

        // 输出未被取消的任务的结果
        for (ResultTask resultTask : resultTasks) {
            try {
                if (!resultTask.isCancelled()) {
                    System.out.printf("%s\n", resultTask.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭执行器
        executor.shutdown();
    }
}
