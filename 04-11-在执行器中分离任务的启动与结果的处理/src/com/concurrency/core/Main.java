package com.concurrency.core;

import com.concurrency.task.ReportProcessor;
import com.concurrency.task.ReportRequest;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // 创建一个执行器对象和三个完成服务对象，执行器对象供完成服务对象使用
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        // 创建两报表请求对象和两个线程来执行他们
        ReportRequest faceRequest = new ReportRequest("Face", service);
        ReportRequest onlineRequest = new ReportRequest("Online", service);
        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);

        // 创建一个报表处理对象和一个执行它的线程
        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);

        // 启动线程
        System.out.printf("Main: Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        senderThread.start();

        // 等待报表生成器对象的任务完成
        try {
            System.out.printf("Main: Waiting for the report generators.\n");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭执行器
        System.out.printf("Main: Shuting down the executor.\n");
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 等待报表发送器对象执行结束
        processor.setEnd(true);
        System.out.printf("Main: Ends\n");
    }
}
