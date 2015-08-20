package com.concurrency.task;

/**
 * This class implements the producers of data. It store 100
 * events in the queue with incremental priority
 *
 */
public class Producer implements Runnable {
	
	/**
	 * Buffer used to store the events
	 */
	private MyPriorityTransferQueue<Event> buffer;
	
	/**
	 * Constructor of the class. It initializes its parameters
	 * @param buffer Buffer to store the events
	 */
	public Producer(MyPriorityTransferQueue<Event> buffer) {
		this.buffer=buffer;
	}
	
	/**
	 * Main method of the producer. Store 100 events in the buffer with
	 * incremental priority
	 */
	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			Event event=new Event(Thread.currentThread().getName(),i);
			buffer.put(event);
		}
	}

}
