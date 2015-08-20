package com.concurrency.task;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 添加数据任务类，向并发队列中添加10000个数据
 */
public class AddTask implements Runnable {

    /**
     * 等待添加数组的队列
     */
    private ConcurrentLinkedDeque<String> list;

    /**
     * 构造函数
     *
     * @param list 等待添加数组的队列
     */
    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    /**
     * 核心方法，向并发队列中添加10000个数据
     */
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + ": Element " + i);
        }
    }

}
