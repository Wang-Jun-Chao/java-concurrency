package com.concurrency.task;

/**
 * This class stores the attributes of an event. Its thread
 * and is priority. Implements the comparable interface to
 * help the priority queue to decide which event has more priority 
 *
 */
public class Event implements Comparable<Event> {
	
	/**
	 * Number of the thread that generates the event
	 */
	private String thread;
	/**
	 * Priority of the thread
	 */
	private int priority;
	
	/**
	 * Constructor of the thread. It initializes its attributes
	 * @param thread Number of the thread that generates the event
	 * @param priority Priority of the event
	 */
	public Event(String thread, int priority){
		this.thread=thread;
		this.priority=priority;
	}
	
	/**
	 * Method that returns the number of the thread that generates the
	 * event
	 * @return The number of the thread that generates the event
	 */
	public String getThread() {
		return thread;
	}
	
	/**
	 * Method that returns the priority of the event
	 * @return The priority of the event
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Method that compares two events and decide which has more priority
	 */
	@Override
	public int compareTo(Event e) {
		if (this.priority>e.getPriority()) {
			return -1;
		} else if (this.priority<e.getPriority()) {
			return 1; 
		} else {
			return 0;
		}
	}
}
