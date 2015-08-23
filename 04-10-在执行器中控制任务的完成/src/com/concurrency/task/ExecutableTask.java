package com.concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 任务执行类，并指定其实现Callable接口，参数化为String类型。
 */
public class ExecutableTask implements Callable<String> {
    /**
     * 任务名称
     */
    private String name;

    public ExecutableTask(String name) {
        this.name = name;
    }

    /**
     * 核心方法，等待一个随机时间，返回一个结果
     *
     * @return 字符串结果
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        return "Hello, world. I'm " + name;
    }

    /**
     * 获取任务名称
     *
     * @return 任务名称
     */
    public String getName() {
        return name;
    }
}
