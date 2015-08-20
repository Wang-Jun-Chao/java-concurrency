package com.concurrency.core;

import com.concurrency.task.BuildStats;
import com.concurrency.task.Sensor1;
import com.concurrency.task.Sensor2;

import java.util.Date;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个建筑物状态对象
        BuildStats stats = new BuildStats();

        // 创建场景1对象，并且让它在一个单独的线程中运行
        Sensor1 sensor1 = new Sensor1(stats);
        Thread thread1 = new Thread(sensor1, "Sensor 1");

        // 创建场景2对象，并且让它在一个单独的线程中运行
        Sensor2 sensor2 = new Sensor2(stats);
        Thread thread2 = new Thread(sensor2, "Sensor 2");

        // 获取实际时间
        Date date1 = new Date();

        // 启动线程
        thread1.start();
        thread2.start();

        try {
            // 等待线程完成
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取实际时间，并且输出实际时间
        Date date2 = new Date();
        stats.printStats();
        System.out.println("Execution Time: " + ((date2.getTime() - date1.getTime()) / 1000));
    }
}
