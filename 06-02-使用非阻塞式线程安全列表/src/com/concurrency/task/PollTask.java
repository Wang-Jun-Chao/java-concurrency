package com.concurrency.task;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 取数据任务类，从并发队列中删除10000个数据
 */
public class PollTask implements Runnable {

    /**
     * 待删除元素的队列
     */
    private ConcurrentLinkedDeque<String> list;

    /**
     * 构造函数
     *
     * @param list 待删除元素的队列
     */
    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    /**
     * 核心方法，在并发队列的头部和尾部各删除5000个元素
     */
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }


}
