package com.concurrency.task;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 拒绝任务处理器类
 */
public class RejectedTaskController implements RejectedExecutionHandler {
    /**
     * 核心方法，在控制台输出已被拒绝的任务的名称和 执行器的状态。
     * @param r
     * @param executor
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskController: The task %s has been rejected\n", r.toString());
        System.out.printf("RejectedTaskController: %s\n", executor.toString());
        System.out.printf("RejectedTaskController: Terminating: %s\n", executor.isTerminating());
        System.out.printf("RejectedTasksController: Terminated: %s\n", executor.isTerminated());
    }
}
