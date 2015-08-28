package com.concurrency.core;

import com.concurrency.task.Event;
import com.concurrency.task.Task;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {

        // 存储事件的优先级队列
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();


        // 存储5个线程对象的数组
        Thread taskThreads[] = new Thread[5];


        // 创建5个线程运行5个任务，每个任务创建1000事件对象
        for (int i = 0; i < taskThreads.length; i++) {
            Task task = new Task(i, queue);
            taskThreads[i] = new Thread(task);
        }


        //  启动5个线程
        for (Thread taskThread : taskThreads) {
            taskThread.start();
        }

        // 等待5个线程完成
        for (Thread taskThread : taskThreads) {
            try {
                taskThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 输出事件信息
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThreads.length * 1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }
}
