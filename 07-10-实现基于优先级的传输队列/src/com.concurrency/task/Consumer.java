package com.concurrency.task;

/**
 * This class implements the Consumer of the events. There is only
 * one consumer in the example that consumes 1002 events
 */
public class Consumer implements Runnable {

    /**
     * Buffer from which the consumer takes the events
     */
    private MyPriorityTransferQueue<Event> buffer;

    /**
     * Constructor of the class. Initializes its attributes
     *
     * @param buffer Buffer from which the consumer takes the events
     */
    public Consumer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    /**
     * Main method of the consumer. It takes 1002 events from the buffer
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
