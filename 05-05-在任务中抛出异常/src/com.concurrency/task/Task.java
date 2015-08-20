package com.concurrency.task;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2014-12-01
 * Time: 09:00
 * Declaration: All Rights Reserved !!!
 */
public class Task extends RecursiveTask<Integer> {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 待处理的数组
     */
    private int array[];

    /**
     * 任务处理的起始位置
     */
    private int start;
    /**
     * 任务处理的结束位置
     */
    private int end;

    /**
     * 构造函数
     *
     * @param array 待处理的数组
     * @param start 任务处理的起始位置
     * @param end   任务处理的结束位置
     */
    public Task(int array[], int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * 核心方法，如果处理的元素大于9个就分成两个任务去执行它，如果处理的起始位置小于3，结束位置大于3就抛出异常
     */
    @Override
    protected Integer compute() {
        System.out.printf("Task: Start from %d to %d\n", start, end);
        if (end - start < 10) {
            if ((3 > start) && (3 < end)) {
                throw new RuntimeException("This task throws an Exception: Task from  " + start + " to " + end);
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            int mid = (end + start) / 2;
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);
            invokeAll(task1, task2);
            System.out.printf("Task: Result form %d to %d: %d\n", start, mid, task1.join());
            System.out.printf("Task: Result form %d to %d: %d\n", mid, end, task2.join());
        }
        System.out.printf("Task: End form %d to %d\n", start, end);
        return 0;
    }

}
