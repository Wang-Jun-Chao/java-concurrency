package com.concurrency.core;

import com.concurrency.task.FolderProcessor;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 创建分合池
        ForkJoinPool pool = new ForkJoinPool();

        // 为三个不同的文件夹创建文件处理器对象
        FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
        FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
        FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");

        // 在分合池中执行一个任务
        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);

        // 输出统计信息，直到三个任务都完成
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
        } while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));

        // 关闭分合池
        pool.shutdown();

        // 保存每个任务统计的结束
        List<String> results;

        results = system.join();
        System.out.printf("System: %d files found.\n", results.size());

        results = apps.join();
        System.out.printf("Apps: %d files found.\n", results.size());

        results = documents.join();
        System.out.printf("Documents: %d files found.\n", results.size());
    }
}
