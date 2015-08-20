package com.concurrency.task;


public class Job implements Runnable {
    /**
     * 打印文档的队列
     */
    private PrintQueue printQueue;

    /**
     * 构造函数
     *
     * @param printQueue 打印文档的队列
     */
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
