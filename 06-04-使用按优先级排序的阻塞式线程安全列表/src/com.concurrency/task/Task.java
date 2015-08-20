package com.concurrency.task;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 任务对象，生成1000个事件，并且将其存放在一个优先队列中
 */
public class Task implements Runnable {

    /**
     * 任务编号
     */
    private int id;

    /**
     * 存储事件的优先队列
     */
    private PriorityBlockingQueue<Event> queue;

    /**
     * 构造函数，初始化属性
     *
     * @param id    任务编号
     * @param queue 存储事件的优先队列
     */
    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    /**
     * 核心方法，生成1000个事件，并且将其存放在一个优先队列中
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }
}
