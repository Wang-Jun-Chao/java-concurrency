package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * This is the base class to implement a priority-based executor. It implements the base for the priority tasks.
 * They are based on the Runnable interface and implement the Comparable interface. 
 * If a task has a higher value of its priority attribute, it will be stored before in the priority queue and
 * it will be executed before
 *
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {

	/**
	 * This attribute stores the priority of the task
	 */
	private int priority;
	
	/**
	 * This attribute stores the name of the task
	 */
	private String name;
	
	/**
	 * Constructor of the task. It initialize its attributes
	 * @param name Name of the task
	 * @param priority Priority of the task
	 */
	public MyPriorityTask(String name, int priority) {
		this.name=name;
		this.priority=priority;
	}
	
	/**
	 * Method that returns the priority of the task
	 * @return the priority of the task
	 */
	public int getPriority(){
		return priority;
	}
	
	/**
	 * Method that compares the priorities of two tasks. The task with higher priority value will
	 * be stored before in the list and it will be executed before
	 */
	@Override
	public int compareTo(MyPriorityTask o) {
		if (this.getPriority() < o.getPriority()) {
			return 1;
		}
		
		if (this.getPriority() > o.getPriority()) {
			return -1;
		}
		
		return 0;
	}

	/**
	 * Main method of the task. It only writes a message to the console. It will be overridden by the real tasks
	 */
	@Override
	public void run() {
		System.out.printf("MyPriorityTask: %s Priority : %d\n",name,priority);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

}
