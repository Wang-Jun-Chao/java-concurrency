package com.concurrency.task;

import com.sun.istack.internal.NotNull;

/**
 * 事件类，存储一个件事的属性，它包括一个事件的优先级，这个类实现了Comparable接口的方法，
 * 用来确定哪个事件对象的优先级更高
 */
public class Event implements Comparable<Event> {

    /**
     * 线程编号
     */
    private int thread;
    /**
     * 线程优先级
     */
    private int priority;

    /**
     * 构造构造，用于初始化属性
     *
     * @param thread   产生事件的线程编号
     * @param priority 事件的优先级
     */
    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    /**
     * 获取线程编号
     *
     * @return 线程编号
     */
    public int getThread() {
        return thread;
    }

    /**
     * 获取线程优先级
     *
     * @return 线程优先级
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 比较那个线程的优先级更高
     */
    @Override
    public int compareTo(Event e) {
        if (this.priority > e.getPriority()) {
            return -1;
        } else if (this.priority < e.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
