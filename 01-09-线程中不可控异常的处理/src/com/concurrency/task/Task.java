package com.concurrency.task;

/**
 * 任务类，专门抛出异常
 */
public class Task implements Runnable {
    @Override
    public void run() {
        // 下面的语句会招聘异常
        int number = Integer.parseInt("TTT");
    }
}
