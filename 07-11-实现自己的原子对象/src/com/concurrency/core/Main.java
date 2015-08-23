package com.concurrency.core;


import com.concurrency.task.ParkingCounter;
import com.concurrency.task.Sensor1;
import com.concurrency.task.Sensor2;

/**
 * Main class of the example
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /*
		 * Create a ParkingCounter object
		 */
        ParkingCounter counter = new ParkingCounter(5);
		
		/*
		 * Create and launch two sensors
		 */
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);

        Thread thread1 = new Thread(sensor1);
        Thread thread2 = new Thread(sensor2);

        thread1.start();
        thread2.start();
		
		/*
		 * Wait for the finalization of the threads
		 */
        thread1.join();
        thread2.join();
		
		/*
		 * Write in the console the number of cars in the parking
		 */
        System.out.printf("Main: Number of cars: %d\n", counter.get());
		
		/*
		 * Writ a message indicating the end of the program
		 */
        System.out.printf("Main: End of the program.\n");
    }
}
