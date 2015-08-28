package com.concurrency.task;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 产生随机数的任务类
 */
public class TaskLocalRandom implements Runnable {

    /**
     * 构造函数，初始化当前类的随机数生成对象
     */
    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    /**
     * 核心方法，生成一个[0, 10)的随机数
     */
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
        }
    }
}
