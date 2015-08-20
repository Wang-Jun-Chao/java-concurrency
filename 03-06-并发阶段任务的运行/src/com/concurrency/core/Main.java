package com.concurrency.core;

import com.concurrency.task.FileSearch;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        // 创建一个阶段对象，它有三个参与者
        Phaser phaser = new Phaser(3);

        // 创建一个文件搜索对象，每一个搜索不同的目录
        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

        // 创建一个线程运行文件搜索对象，并且启动线程
        Thread systemThread = new Thread(system, "System");
        systemThread.start();

        // 创建一个线程运行文件搜索对象，并且启动线程
        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();

        // 创建一个线程运行文件搜索对象，并且启动线程
        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();

        // 等待所有的线程都结束
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Terminated: %s\n", phaser.isTerminated());
    }
}
