package com.concurrency.core;

import com.concurrency.task.MyPriorityTask;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // 线程执行器
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1,
                TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());


        // 向执行器中添加4个任务
        for (int i = 0; i < 4; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
        }


        // 主线程序休眠1S
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 再向执行器中添加4个任务
        for (int i = 4; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
        }


        // 关闭执行器
        executor.shutdown();

        // 等所有的任务运行结束
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出信息表示任务运行结束
        System.out.printf("Main: End of the program.\n");
    }

}
