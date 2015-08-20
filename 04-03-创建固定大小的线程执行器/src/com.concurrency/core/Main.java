package com.concurrency.core;

import com.concurrency.task.Server;
import com.concurrency.task.Task;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        // 发送100个任务到服务器对象，并且完成任务
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }

        server.endServer();
    }
}
