package com.concurrency.core;

import com.concurrency.task.MyLock;
import com.concurrency.task.Task;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // 创建一个自定义的锁对象
        MyLock lock = new MyLock();

        // 创建10个运行任务对象
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task-" + i, lock);
            Thread thread = new Thread(task);
            thread.start();
        }

        // 主线程试图获取锁
        boolean value;
        do {
            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                if (!value) {
                    System.out.printf("Main: Trying to get the Lock\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);

        // 主线程释放锁
        System.out.printf("Main: Got the lock\n");
        lock.unlock();

        // 输出信息，表明程序运行结束
        System.out.printf("Main: End of the program\n");
    }

}
