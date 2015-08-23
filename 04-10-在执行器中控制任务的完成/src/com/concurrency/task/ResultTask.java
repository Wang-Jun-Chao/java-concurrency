package com.concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 结果任务类，这个类管理着可执行任务类的执行
 */
public class ResultTask extends FutureTask<String> {
    /**
     * 结果任务的名称
     */
    private String name;

    /**
     * 构造函数
     *
     * @param callable 可调用的接口对象
     */
    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask) callable).getName();
    }

    /**
     * 当任务完成时调用这个方法
     */
    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s: Has been cancelled\n", name);
        } else {
            System.out.printf("%s: Has finished\n", name);
        }
    }
}
