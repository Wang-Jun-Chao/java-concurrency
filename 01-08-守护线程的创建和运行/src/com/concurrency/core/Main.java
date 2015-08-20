package com.concurrency.core;

import com.concurrency.event.Event;
import com.concurrency.task.CleanerTask;
import com.concurrency.task.WriterTask;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        // 创建一个用于存放事件对象的队列
        Deque<Event> deque = new ArrayDeque<Event>();

        // 创建一个写任务的对象，并且创建三个线程去调用这个对象
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }

        // 创建一个事件清除任务，并且启动这个任务
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();

    }
}
