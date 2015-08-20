package com.concurrency.core;

import com.concurrency.task.Calculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

public class Main {
    public static void main(String[] args) {

        // 线程优先级信息
        System.out.printf("Minimum Priority: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Normal  Priority: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Maximum Priority: %s\n", Thread.MAX_PRIORITY);

        Thread threads[];
        Thread.State status[];

        // 运行10个线程，5个线程的使用最高优先级，5个线程使用最低优先级
        threads = new Thread[10];
        status = new Thread.State[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);
        }

        // 等待线程完成，同时将线程状态信息写入到文件中
        PrintWriter pw = null;
        try {
            // 获取项目运行的根路径
            String configFile = Main.class.getClassLoader().getResource("").getPath();
            configFile = URLDecoder.decode(configFile, "utf-8");

            System.out.println(configFile);

            File logFile = new File(configFile + "/data/log.txt"); // 创建一个记录文件对象

            if(!logFile.getParentFile().exists()) {    // 如果目录不存在就创建目录
                logFile.getParentFile().mkdirs();
            }

            if (!logFile.exists()) { //如果文件不存在就创建一个文件
                logFile.createNewFile();
            }

            FileWriter file = new FileWriter(logFile);
            pw = new PrintWriter(file);

            for (int i = 0; i < 10; i++) {
                pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) { // 如果线程状态发生了变化
                        writeThreadInfo(pw, threads[i], status[i]); // 将线程变化之前的状态写入文件
                        status[i] = threads[i].getState(); // 记录新的状态
                    }
                }

                finish = true;
                for (int i = 0; i < 10; i++) {
                    // 如果所有线程都终止了finish就为true
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 将线程状态信息写入到一个文件中
     *
     * @param pw     写数据的流
     * @param thread 信息要被写入文件的线程
     * @param state  线程的前一个状态
     */
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d ---- %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority:  %d\n", thread.getPriority());
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ************************************\n");

    }
}
