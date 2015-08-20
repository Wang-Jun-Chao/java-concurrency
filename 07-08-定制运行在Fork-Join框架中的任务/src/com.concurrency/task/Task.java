package com.concurrency.task;

/**
 * Task that extends the MyWorkerTask class to be executed
 * in a Fork/Join framework
 */
public class Task extends MyWorkerTask {

    /**
     * Serival Version UID of the task
     */
    private static final long serialVersionUID = 1L;

    /**
     * Array of integers. This task will increment all the elements of the array
     */
    private int array[];
    /**
     * First element of the array that this task is going to increment
     */
    private int start;

    /**
     * Last element of the array that this task is going to increment
     */
    private int end;

    /**
     * Constructor of the class. It initializes its attributes
     *
     * @param name  Name of the task
     * @param array Array of elements that is going to be incremented
     * @param start First element of the array to be incremented by this task
     * @param end   Last element of the array to be incremented by this task
     */
    public Task(String name, int array[], int start, int end) {
        super(name);
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * Main method of the task. If the task has to increment less that 100
     * elements, it increments them directly. Else, it divides the
     * operation in two subtasks
     */
    @Override
    protected void compute() {
        if (end - start > 100) {
            int mid = (end + start) / 2;
            Task task1 = new Task(this.getName() + "1", array, start, mid);
            Task task2 = new Task(this.getName() + "2", array, mid, end);
            invokeAll(task1, task2);
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
