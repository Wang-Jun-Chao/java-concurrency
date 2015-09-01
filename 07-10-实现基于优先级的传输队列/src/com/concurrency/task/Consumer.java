package com.concurrency.task;

/**
 * 消费者类
 */
public class Consumer implements Runnable {


    private MyPriorityTransferQueue<Event> buffer;

    public Consumer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    /**
     * 消费1002个Event(例子中所有产生的事件), 并在控制台输出产生事件的线程的名称以及事件的优先级priority。
     */
    @Override
    public void run() {
        for (int i = 0; i < 1002; i++) {
            try {
                Event value = buffer.take();
                System.out.printf("Consumer: %s: %d\n", value.getThread(), value.getPriority());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
