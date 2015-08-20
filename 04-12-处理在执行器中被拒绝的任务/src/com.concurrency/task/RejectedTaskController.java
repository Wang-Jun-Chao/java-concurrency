package com.concurrency.task;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: 王俊超
 * Date: 2014-11-30
 * Time: 16:55
 * Declaration: All Rights Reserved !!!
 */
public class RejectedTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskController: The task %s has been rejected\n",r.toString());
        System.out.printf("RejectedTaskController: %s\n",executor.toString());
        System.out.printf("RejectedTaskController: Terminating: %s\n",executor.isTerminating());
        System.out.printf("RejectedTasksController: Terminated: %s\n",executor.isTerminated());
    }
}
