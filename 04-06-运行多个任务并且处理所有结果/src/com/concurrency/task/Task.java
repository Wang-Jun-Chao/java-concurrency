package com.concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 任务类，实现Callable接口，参数化为Result类型。
 */
public class Task implements Callable<Result> {
    /**
     * 任务的名称
     */
    private String name;

    /**
     * 构造函数
     *
     * @param name 初始化任务的名称
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * 核心方法，等待一个随机时间，并且计算5个随机数的和
     *
     * @return 个随机数的和
     * @throws Exception
     */
    @Override
    public Result call() throws Exception {
        // 向控制台输出信息
        System.out.printf("%s: Staring\n", this.name);

        // 等待一个随机的时间
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 计算随机数的和
        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += (int) (Math.random() * 100);

        }

        // 创建一个结果对象
        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);
        System.out.printf("%s: Ends\n", this.name);

        // 返回结果对象
        return result;
    }
}
