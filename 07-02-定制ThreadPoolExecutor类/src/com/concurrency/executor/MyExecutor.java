package com.concurrency.executor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展的线程池执行器类，实现定制执行器功能。
 */
public class MyExecutor extends ThreadPoolExecutor {

    /**
     * 用户存存储任务的名称（线程的hashCode字符形式）和它开始执行的时间
     */
    private ConcurrentHashMap<String, Date> startTimes;

    /**
     * 构造函数
     *
     * @param corePoolSize    线程池最小的线程数目
     * @param maximumPoolSize 线程池最大的线程数目
     * @param keepAliveTime   线程最大的空闲时间
     * @param unit            空闲时间的时间单位
     * @param workQueue       提交的任务所使用的队列
     */
    public MyExecutor(int corePoolSize,
                      int maximumPoolSize,
                      long keepAliveTime,
                      TimeUnit unit,
                      BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }


    /**
     * 这个方法被用来结束执行器的执行，输出线程池的相关信息
     */
    @Override
    public void shutdown() {
        System.out.printf("MyExecutor: Going to shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        super.shutdown();
    }

    /**
     * 这个方法被用来立即结束线程池的执行， 输出线程池的相关信息
     */
    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("MyExecutor: Going to immediately shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        return super.shutdownNow();
    }

    /**
     * 线程执行之前所调用的方法，在这时是打印线程信息，和存储线程信息
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("MyExecutor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
        startTimes.put(String.valueOf(r.hashCode()), new Date());
    }

    /**
     * 在线程执行完成后执行的方法，在这里是输出线程的信息和它执行的时间
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;
        try {
            System.out.printf("*********************************\n");
            System.out.printf("MyExecutor: A task is finishing.\n");
            System.out.printf("MyExecutor: Result: %s\n", result.get());
            Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
            Date finishDate = new Date();
            long diff = finishDate.getTime() - startDate.getTime();
            System.out.printf("MyExecutor: Duration: %d\n", diff);
            System.out.printf("*********************************\n");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
