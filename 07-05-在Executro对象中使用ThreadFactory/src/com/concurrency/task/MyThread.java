package com.concurrency.task;

import java.util.Date;

/**
 * This class implement your own Thread. It stores the creation date, the
 * start date and the finish date of the thread. It provides a mehtod that
 * calculates the execution time of the thread. Overrides the toString() method
 * to return information about the creationDate and the execution time of the thread
 */
public class MyThread extends Thread {
	
	/**
	 * Creation date of the thread
	 */
	private Date creationDate;
	
	/**
	 * Start date of the thread
	 */
	private Date startDate;
	
	/**
	 * Finish date of the thread
	 */
	private Date finishDate;
	
	/**
	 * Constructor of the class. It establishes the value of the creation date attribute
	 * @param target Runnable object that this thread is going to execute
	 * @param name Name of the thread
	 */
	public MyThread(Runnable target, String name ){
		super(target,name);
		setCreationDate();
	}

	/**
	 * Main method of the thread. Stores the start and finish date of the thread and calls
	 * the run() method of its parent class
	 */
	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
		System.out.printf("Thread: %s\n",toString());
	}
	
	/**
	 * Method that establish the creation date of the thread
	 */
	public void setCreationDate() {
		creationDate=new Date();
	}
	
	/**
	 * Method that establish the start date of the thread
	 */
	public void setStartDate() {
		startDate=new Date();
	}
	
	/**
	 * Method that establish the finish date of the thread
	 */
	public void setFinishDate() {
		finishDate=new Date();
	}
	
	/**
	 * Method that calculates the execution time of the thread as the difference
	 * between the finish date and the start date.
	 * @return
	 */
	public long getExecutionTime() {
		long ret;
		ret=finishDate.getTime()-startDate.getTime();
		return ret;
	}
	
	/**
	 * Method that generates a String with information about the creation date and the
	 * execution time of the thread
	 */
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Creation Date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}
}
