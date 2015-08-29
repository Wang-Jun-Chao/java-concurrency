package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义任务高度类，它有一个参数化类型V
 *
 * @param <V>
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {

    // 用于存储可调度的任务对象
    private RunnableScheduledFuture<V> task;

    // 可调度的线程池执行器
    private ScheduledThreadPoolExecutor executor;

    // 任务两次执行的时间间隔
    private long period;

    // 任务开始执行的时间
    private long startDate;

    /**
     * 构造函数
     *
     * @param runnable 任务提交的可执行的任务对象
     * @param result   任务返回的结果
     * @param task     执行runnable对象的任务
     * @param executor 执行task对象的执行器
     */
    public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    /**
     * 返回下一次要执行的剩余时间，如是延迟任务就返回最初任务的延迟时间，
     * 如果是周期任务，返回开始时间和当前时间的差值
     *
     * @param unit 延迟的时间单位
     */
    @Override
    public long getDelay(TimeUnit unit) {
        if (!isPeriodic()) {
            return task.getDelay(unit);
        } else {
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                Date now = new Date();
                long delay = startDate - now.getTime();
                return unit.convert(delay, TimeUnit.MILLISECONDS);
            }
        }
    }

    /**
     * 比较方法
     */
    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    /**
     * 判断是否是周期任务
     */
    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }


    /**
     * 主方法
     */
    @Override
    public void run() {
        // 如果是周期任务，并且执行器没有关闭
        if (isPeriodic() && (!executor.isShutdown())) {
            // 更新开始时间，同时将本任务再次入队
            Date now = new Date();
            startDate = now.getTime() + period;
            executor.getQueue().add(this);
        }

        // 输出任务相关信息
        System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
        System.out.printf("MyScheduledTask: Is Periodic: %s\n", isPeriodic());
        super.runAndReset();
        System.out.printf("Post-MyScheduledTask: %s\n", new Date());
    }

    /**
     * 设置周期任务的时间间隔
     *
     * @param period 时间间隔
     */
    public void setPeriod(long period) {
        this.period = period;
    }
}
