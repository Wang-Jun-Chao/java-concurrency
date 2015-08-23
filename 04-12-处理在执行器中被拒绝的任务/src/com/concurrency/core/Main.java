package com.concurrency.core;

import com.concurrency.task.RejectedTaskController;
import com.concurrency.task.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        // 创建拒绝任务控制器对象
        // Create the controller for the Rejected tasks
        RejectedTaskController controller = new RejectedTaskController();
        // 创建执行器对象，并且设置拒绝执行处理器对象
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(controller);

        // 运行三个任务
        System.out.printf("Main: Starting.\n");
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task" + i);
            executor.submit(task);
        }

        // 关闭执行器
        System.out.printf("Main: Shuting down the Executor.\n");
        executor.shutdown();

        // 发送另外一个任务
        System.out.printf("Main: Sending another Task.\n");
        Task task = new Task("RejectedTask");
        executor.submit(task);

        // 程序结束
        System.out.printf("Main: End.\n");
    }
}
