package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * 具有指定优先级的任务
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {

    /**
     * 任务优先级
     */
    private int priority;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 构造函数
     *
     * @param name     任务名称
     * @param priority 任务优先级
     */
    public MyPriorityTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * 获取任务优先级
     *
     * @return 任务优先级
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 比较方法，与另一个任务对象进行比较
     */
    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        }

        if (this.getPriority() > o.getPriority()) {
            return -1;
        }

        return 0;
    }

    /**
     * 主方法，运行两秒钟（休息两秒钟）
     */
    @Override
    public void run() {
        System.out.printf("MyPriorityTask: %s Priority : %d\n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
