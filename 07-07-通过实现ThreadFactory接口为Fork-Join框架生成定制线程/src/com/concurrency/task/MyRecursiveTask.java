package com.concurrency.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 自定义递归任务类
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    // 待计算的数组
    private int array[];

    // 计算的起始和结束位置
    private int start, end;

    /**
     * 构造函数，初始化任务属性
     *
     * @param array 待计算数组
     * @param start 计算的起始位置
     * @param end   计算的结束位置
     */
    public MyRecursiveTask(int array[], int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * 主方法，如果进行计算的数目大于100个就会成两个字任务进行执行
     */
    @Override
    protected Integer compute() {
        Integer ret;
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        thread.addTask();
        if (end - start > 100) {
            int mid = (start + end) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(array, start, mid);
            MyRecursiveTask task2 = new MyRecursiveTask(array, mid, end);
            invokeAll(task1, task2);
            ret = addResults(task1, task2);
        } else {
            int add = 0;
            for (int i = start; i < end; i++) {
                add += array[i];
            }
            ret = new Integer(add);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 归并两个子任务的结果
     *
     * @param task1 First task
     * @param task2 Second task
     * @return The sum of the results of the two tasks
     */
    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value;
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        return new Integer(value);
    }

}
