package com.concurrency.core;

import com.concurrency.task.Task;

import java.util.Date;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // 创建一个定时行器服务对象
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n", new Date());

        // 创建一个执行任务，并且将它放入执行器中，初始延迟是1秒，周期是2秒
        Task task = new Task("Task");
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        // 控制任务的执行
        for (int i = 0; i < 10; i++) {
            System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 关闭执行器对象
        executor.shutdown();
        System.out.printf("Main: No more tasks at: %s\n", new Date());

        // 验证在执行器关闭后，周期性任务不会执行
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出完成信息
        System.out.printf("Main: Finished at: %s\n", new Date());
    }
}
