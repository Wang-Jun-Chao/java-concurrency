package com.concurrency.core;

import com.concurrency.task.FileClock;

import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) {
        // 创建一个文件时间运行对象，并且将其放入一个线程对象中
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);

        // 开始线程
        thread.start();
        try {
            // 等待五秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程
        thread.interrupt();
    }
}
