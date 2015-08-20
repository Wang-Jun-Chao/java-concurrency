package com.concurrency.core;

import com.concurrency.task.MyPhaser;
import com.concurrency.task.Student;


public class Main {
    public static void main(String[] args) {
        // 创建一个阶段对象
        MyPhaser phaser = new MyPhaser();
        // 创建一个学生对象，将它们注册到同一个阶段对象中
        Student[] students = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(phaser);
            phaser.register();
        }

        // 创建五个线程来运行五个学生对象，并且启动线程
        Thread[] threads = new Thread[5];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i]);
            threads[i].start();
        }

        // 等待所有线程完成执行
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查阶段是否已经到达完成状态
        System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
    }
}
