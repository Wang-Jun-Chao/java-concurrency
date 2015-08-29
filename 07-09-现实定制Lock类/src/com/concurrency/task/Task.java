package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * 自定义任务类
 */
public class Task implements Runnable {

    // 使用自定义锁对象
    private MyLock lock;

    // 任务名称
    private String name;

    /**
     * 构造函数
     *
     * @param name 任务名称
     * @param lock 使用的锁
     */
    public Task(String name, MyLock lock) {
        this.lock = lock;
        this.name = name;
    }

    /**
     * 主方法，运行两秒种（其实就是休眠）
     */
    @Override
    public void run() {
        lock.lock();
        System.out.printf("Task: %s: Take the lock\n", name);
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("Task: %s: Free the lock\n", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
