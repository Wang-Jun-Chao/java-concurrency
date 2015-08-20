package com.concurrency.core;

import com.concurrency.task.DocumentTask;
import com.concurrency.utils.DocumentMock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2014-11-23
 * Time: 08:38
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个模拟文档，他有100行，每行1000个单词
        // Generate a document with 100 lines and 1000 words per line
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 1000, "the");

        // 创建一个文档任务对象，处理整个文档
        DocumentTask task = new DocumentTask(document, 0, 100, "the");

        // 创建一个分合池对象
        ForkJoinPool pool = new ForkJoinPool();

        // 执行文档处理任务
        pool.execute(task);

        // 输出分合池对象的统计数据
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!task.isDone());

        // 关闭分合池
        pool.shutdown();

        // 等待所有的任务完成
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出任务完成的结果
        try {
            System.out.printf("Main: The word appears %d in the document", task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
