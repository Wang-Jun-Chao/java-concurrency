package com.concurrency.core;

import com.concurrency.task.Consumer;
import com.concurrency.task.EventStorage;
import com.concurrency.task.Producer;

public class Main {
    public static void main(String[] args) {
        // 创建一个事件存储对象
        EventStorage storage = new EventStorage();

        // 创建一个事件生产者对象，并且将其放入到一个线程中运行
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);

        // 创建一个事件消费者对象，并且将其放入到一个线程中运行
        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        // 启动两线程
        thread2.start();
        thread1.start();
    }
}
