package com.concurrency.core;

import com.concurrency.task.Event;
import com.concurrency.task.Task;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 存储事件的延迟队列
        DelayQueue<Event> queue = new DelayQueue<>();

        // 线程数组
        Thread threads[] = new Thread[5];

        // 创建5个任务对象，并且放在不同的线程中去执行
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i + 1, queue);
            threads[i] = new Thread(task);
        }

        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待5个任务的完成
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出结果
        do {
            int counter = 0;
            Event event;
            do { // 取队列中的所有数据
                event = queue.poll();
                if (event != null) {
                    counter++;
                }
            } while (event != null);
            System.out.printf("At %s you have read %d events\n", new Date(), counter);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }
}
