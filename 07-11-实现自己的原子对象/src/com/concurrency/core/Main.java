package com.concurrency.core;


import com.concurrency.task.ParkingCounter;
import com.concurrency.task.Sensor1;
import com.concurrency.task.Sensor2;

public class Main {

    public static void main(String[] args) throws Exception {
        // 停车计数器
        ParkingCounter counter = new ParkingCounter(5);
		
		// 创建两个场景对象
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);

        Thread thread1 = new Thread(sensor1);
        Thread thread2 = new Thread(sensor2);

        thread1.start();
        thread2.start();
		
		// 等待两个线运行结束
        thread1.join();
        thread2.join();
		
		// 停车场中的车的数目
        System.out.printf("Main: Number of cars: %d\n", counter.get());
		
		// 输出信息，表明程序运行结束
        System.out.printf("Main: End of the program.\n");
    }
}
