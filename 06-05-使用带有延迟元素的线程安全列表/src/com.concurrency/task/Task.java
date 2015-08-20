package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * 任务类，其事件存储在一个延迟队列中
 */
public class Task implements Runnable {

    /**
     * 任务编号
     */
    private int id;

    /**
     * 存储事件的任务队列
     */
    private DelayQueue<Event> queue;

    /**
     * 构造函数，初始化属性
     *
     * @param id    任务编号
     * @param queue 存储事件的任务队列
     */
    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }


    /**
     * 核心方法，产生1000事件，每个事件有相同的激活时间，将这些事件存储在延迟队列中
     */
    @Override
    public void run() {

        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + (id * 1000));

        System.out.printf("Thread %s: %s\n", id, delay);

        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }

}
