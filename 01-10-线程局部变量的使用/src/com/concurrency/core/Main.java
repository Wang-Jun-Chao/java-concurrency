package com.concurrency.core;

import com.concurrency.task.UnsafeTask;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 创建线程不安全的任务
        UnsafeTask task = new UnsafeTask();

        // 将任务入进三个不同的线程中
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

