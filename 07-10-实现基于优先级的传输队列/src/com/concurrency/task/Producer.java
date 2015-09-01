package com.concurrency.task;

/**
 * 产生者类
 */
public class Producer implements Runnable {

    // // 用来存储由这个类生成的事件
    private MyPriorityTransferQueue<Event> buffer;

    public Producer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    /**
     * 创建100个Event对象，使用创建序号作为优先级（事件创 建的越晚优先级越高)，
     * 并使用put()方法将它们插入到队列中。
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event(Thread.currentThread().getName(), i);
            buffer.put(event);
        }
    }
}
