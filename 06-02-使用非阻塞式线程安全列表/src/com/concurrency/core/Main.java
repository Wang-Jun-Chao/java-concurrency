package com.concurrency.core;

import com.concurrency.task.AddTask;
import com.concurrency.task.PollTask;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 创建一个并发双向队列对象
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        // 创建长度为100的线程数组
        Thread threads[] = new Thread[100];

        // 创建100个AddTask对象，并且让他们在各自的线程中运行
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);

        // 等待所有的线程执行完
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出队列长度信息
        System.out.printf("Main: Size of the List: %d\n", list.size());

        // 创建100个PollTask对象，并且让他们在各自的线程中运行
        for (int i = 0; i < threads.length; i++) {
            PollTask task = new PollTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);

        // 等待线程执行完
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出队列长度信息
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }
}
