package com.concurrency.task;

/**
 * 生产者对象，生产事件
 */
public class Producer implements Runnable {
    /**
     * 事件存储对象
     */
    private EventStorage storage;

    /**
     * 构造函数
     *
     * @param storage 事件存储对象
     */
    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.storage.set(); // 产生事件
        }
    }
}
