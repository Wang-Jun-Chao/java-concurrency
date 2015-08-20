package com.concurrency.core;

import com.concurrency.task.TaskLocalRandom;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 长度为3的线程数组
        Thread threads[] = new Thread[3];

        // 创建线程并且运行任务
        for (int i = 0; i < threads.length; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }
}
