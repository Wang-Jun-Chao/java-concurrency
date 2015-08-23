package com.concurrency.task;
import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * This class extends the ForkJoinTask class to implement your own version of a task running 
 * in a ForkJoinPool of the Frok/Join framework. It's equivalent to the RecursiveAction and
 * Recursive classes. As the RecursiveAction class, it doesn't return any result
 *
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

	/**
	 * Serial Version UID of the class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the task 
	 */
	private String name;
	
	/**
	 * Constructor of the class. Initializes its attributes 
	 * @param name Name of the task
	 */
	public MyWorkerTask(String name) {
		this.name=name;
	}

	/**
	 * Method that returns the result of the task. In this case, as 
	 * the task doesn't return a result, it returns a null value
	 */
	@Override
	public Void getRawResult() {
		return null;
	}

	/**
	 * Method that establish the result of the task. In this case, as
	 * the task doesn't return a result, this method is empty
	 */
	@Override
	protected void setRawResult(Void value) {
		
	}

	/**
	 * Main method of the task. Is called by the Fork/Join pool. It calls
	 * the compute() method that is an abstract method that have to be
	 * implemented by the tasks that extend this class, calculating its execution
	 * time and writing it in the console
	 */
	@Override
	protected boolean exec() {
		Date startDate=new Date();
		compute();
		Date finishDate=new Date();
		long diff=finishDate.getTime()-startDate.getTime();
		System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n",name,diff);
		return true;
	}

	/**
	 * Method that returns the name of the console
	 * @return The name of the task
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Main method of the child tasks. It has to be overridden in the child classes 
	 * and implement on it its main logic
	 */
	protected abstract void compute();
}
