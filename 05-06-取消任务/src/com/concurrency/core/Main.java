package com.concurrency.core;

import com.concurrency.task.TaskManager;
import com.concurrency.utils.ArrayGenerator;
import com.concurrency.utils.SearchNumberTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 创建一个数组生成器对象，生成一个长度为1000的整形数组
        ArrayGenerator generator = new ArrayGenerator();
        int array[] = generator.generateArray(1000);

        // 创建一个任务管理对象
        TaskManager manager = new TaskManager();

        // 使用默认的构造函数，创建一个分合池对象
        ForkJoinPool pool = new ForkJoinPool();

        // 创建一个处理任务的数组
        SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);

        //执行任务
        pool.execute(task);

        // 关闭这个池
        pool.shutdown();


        // 等待任务完成
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出信息，表示程序已经完成
        System.out.printf("Main: The program has finished\n");
    }
}
