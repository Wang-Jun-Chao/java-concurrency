package com.concurrency.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Task that will be executed in the Fork/Join framework. It calculates
 * the sum of all array elements
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Array to be summed
     */
    private int array[];

    /**
     * Start and end positions of the part of the array to be summed by this task
     */
    private int start, end;

    /**
     * Constructor of the class. It initializes the  attributes of the task
     *
     * @param array Array to be summed
     * @param start Start position of the block of the array to be summed by this task
     * @param end   End position of the block of the array to be summed by this task
     */
    public MyRecursiveTask(int array[], int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * Main method of the task. If the task has less than 100 elements to sum, it calculates
     * the sum of these elements directly. Else, it creates two subtask to process the two
     * halves of the block.
     * <p/>
     * It also calls the addTask() method of the thread that is executing the task to
     * updates its internal counter of tasks
     */
    @Override
    protected Integer compute() {
        Integer ret;
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        thread.addTask();
        if (end - start > 100) {
            int mid = (start + end) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(array, start, mid);
            MyRecursiveTask task2 = new MyRecursiveTask(array, mid, end);
            invokeAll(task1, task2);
            ret = addResults(task1, task2);
        } else {
            int add = 0;
            for (int i = start; i < end; i++) {
                add += array[i];
            }
            ret = new Integer(add);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * Method that adds the results of the two subtasks create by this task
     *
     * @param task1 First task
     * @param task2 Second task
     * @return The sum of the results of the two tasks
     */
    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value;
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        return new Integer(value);
    }

}
