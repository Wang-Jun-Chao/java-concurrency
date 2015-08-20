package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2014-11-30
 * Time: 16:54
 * Declaration: All Rights Reserved !!!
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("Task %s: Starting\n", name);
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task %s: Ending\n", name);
    }

    @Override
    public String toString() {
        return name;
    }
}
