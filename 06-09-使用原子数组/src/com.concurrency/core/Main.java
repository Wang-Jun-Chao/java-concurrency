package com.concurrency.core;

import com.concurrency.task.Decrementer;
import com.concurrency.task.Incrementer;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 线程个数
        final int THREADS = 100;
        // 原子数组对象，它有1000个元素
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);
        // 创建一个加法器对象
        Incrementer incrementer = new Incrementer(vector);
        // 创建一个减法器对象
        Decrementer decrementer = new Decrementer(vector);

        // 创建并且执行100个加法线程和100个减法线程
        Thread threadIncrementer[] = new Thread[THREADS];
        Thread threadDecrementer[] = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threadIncrementer[i] = new Thread(incrementer);
            threadDecrementer[i] = new Thread(decrementer);

            threadIncrementer[i].start();
            threadDecrementer[i].start();
        }

        // 等待所有的任务完成
        for (int i = 0; i < THREADS; i++) {
            try {
                threadIncrementer[i].join();
                threadDecrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 输出不为0的元素
        for (int i = 0; i < vector.length(); i++) {
            if (vector.get(i) != 0) {
                System.out.println("Vector[" + i + "] : " + vector.get(i));
            }
        }

        System.out.println("Main: End of the example");
    }
}
