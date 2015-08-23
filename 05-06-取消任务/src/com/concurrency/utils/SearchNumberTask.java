package com.concurrency.utils;

import com.concurrency.task.TaskManager;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 数值查找类，在数组中查找指定的数值
 */
public class SearchNumberTask extends RecursiveTask<Integer> {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 如果没有找到指定的值就返回-1
     */
    private final static int NOT_FOUND = -1;

    /**
     * 待查找的数据
     */
    private int numbers[];

    /**
     * 数组处理的开始位置
     */
    private int start;
    /**
     * 数据处理的结束位置
     */
    private int end;

    /**
     * 要查找的数值
     */
    private int number;

    /**
     * 任务管理器对象，可以对任务进行取消操作
     */
    private TaskManager manager;

    /**
     * 构造函数，初始化属性
     *
     * @param numbers 待查找的数据
     * @param start   数组处理的开始位置
     * @param end     数据处理的结束位置
     * @param number  要查找的数值
     * @param manager 任务管理器对象
     */
    public SearchNumberTask(int numbers[], int start, int end, int number, TaskManager manager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    /**
     * 核心方法，如查处理的数组数目大于10就调用launchTasks()方法，否则lookForNumber()
     *
     * @return 查找到的位置
     */
    @Override
    protected Integer compute() {
        System.out.println("Task: " + start + ":" + end);
        int ret;
        if (end - start > 10) {
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }
        return ret;
    }

    /**
     * 数据查找方法，找出[start, end)中第一次number出现的位置
     *
     * @return [start, end)中第一次number出现的位置
     */
    private int lookForNumber() {
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.printf("Task: Number %d found in position %d\n", number, i);
                manager.cancelTasks(this); // 取消任务的执行
                return i;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return NOT_FOUND;
    }


    /**
     * 运行任务的方法，分成 两个任务运行
     *
     * @return [start, end)中第一次number出现的位置
     */
    private int launchTasks() {
        int mid = (start + end) / 2;

        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, manager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, mid, end, number, manager);

        manager.addTask(task1);
        manager.addTask(task2);

        task1.fork();
        task2.fork();
        int returnValue;

        returnValue = task1.join();
        if (returnValue != -1) {
            return returnValue;
        }

        returnValue = task2.join();
        return returnValue;
    }

    public void writeCancelMessage() {
        System.out.printf("Task: Cancelled task from %d to %d\n", start, end);
    }
}
