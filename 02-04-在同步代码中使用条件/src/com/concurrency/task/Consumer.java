package com.concurrency.task;

/**
 * 消费者对象，消费事件
 */
public class Consumer implements Runnable {
    /**
     * 事件存储对象
     */
    private EventStorage storage;

    /**
     * 构造函数
     *
     * @param storage 事件存储对象
     */
    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.storage.get(); // 消费事件
        }
    }
}
