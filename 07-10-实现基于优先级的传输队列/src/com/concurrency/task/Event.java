package com.concurrency.task;

/**
 * 自定义事件对象
 */
public class Event implements Comparable<Event> {

    // 产生事件的线程名
    private String thread;

    // 线程优先级
    private int priority;

    /**
     * 构造函数
     *
     * @param thread   产生事件的线程名
     * @param priority 线程优先级
     */
    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public String getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

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
