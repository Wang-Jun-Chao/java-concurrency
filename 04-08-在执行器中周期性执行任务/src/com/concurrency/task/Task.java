package com.concurrency.task;

import java.util.Date;

/**
 * 任务类，执行任务
 */
public class Task implements Runnable {
    /**
     * 任务的名称
     */
    private String name;

    /**
     * 构造函数，初始化任务名称
     *
     * @param name 任务名称
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * 核心方法，向控制台输出当前执行的时间
     */
    @Override
    public void run() {
        System.out.printf("%s: Executed at: %s\n",name,new Date());
    }
}
