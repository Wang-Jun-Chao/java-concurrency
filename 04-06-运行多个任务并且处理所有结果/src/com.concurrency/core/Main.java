package com.concurrency.core;

import com.concurrency.task.Result;
import com.concurrency.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 使用Executors类的newCachedThreadPool()方法，创建ThreadPoolExecutor对象。
        ExecutorService executor = Executors.newCachedThreadPool();

        // 创建三个Task对象，并且将他们存储在一个链表当中
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task-" + i);
            taskList.add(task);
        }

        // 创建Future对象列表，参数化为Result类型。
        List<Future<Result>> resultList = null;
        try {
            // 调用ThreadPoolExecutor类的invokeAll()方法。这个类将会返回之前创建的Future对象列表。
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 使用shutdown()方法结束执行器的执行。
        executor.shutdown();

        // 将结果写入控制台
        System.out.printf("Core: Printing the results\n");
        for (Future<Result> future : resultList) {
            try {
                Result result = future.get();
                System.out.printf("%s: %s\n", result.getName(), result.getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
