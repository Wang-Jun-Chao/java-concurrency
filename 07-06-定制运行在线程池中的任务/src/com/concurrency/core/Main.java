package com.concurrency.core;

import com.concurrency.task.MyScheduledThreadPoolExecutor;
import com.concurrency.task.Task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

        // 创建一个自定义的调度线程池执行器框架
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);

        // 创建一个任务
        Task task = new Task();

        // 输出开始执行的时间
        System.out.printf("Main: %s\n", new Date());

        // 向执行器中发送一个任务，它在一秒后执行
        executor.schedule(task, 1, TimeUnit.SECONDS);

        // 主线程休眠3秒
        TimeUnit.SECONDS.sleep(3);

        // 创建一个任务
        task = new Task();

        // 输出开始执行的时间
        System.out.printf("Main: %s\n", new Date());

        // 送一个任务到执行器，它在一秒后执行，并且分每隔三秒执行一次
        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        // 主线程休眠10秒
        TimeUnit.SECONDS.sleep(10);

        // 关闭执行器
        executor.shutdown();

        // 待待执行器运行结束
        executor.awaitTermination(1, TimeUnit.DAYS);

        // 输出信息，通知程序进行结束
        System.out.printf("Main: End of the program.\n");
    }

}
