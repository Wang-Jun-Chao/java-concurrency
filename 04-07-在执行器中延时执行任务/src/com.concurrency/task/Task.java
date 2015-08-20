package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 任务类，实现Callable接口，参数化为String类型。
 * Author: 王俊超
 * Date: 2014-11-28
 * Time: 10:42
 * Declaration: All Rights Reserved !!!
 */
public class Task implements Callable<String> {
    /**
     * 任务名称
     */
    private String name;

    /**
     * 构造函数，初始化任务名称
     *
     * @param name 任务名称
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * 核心方法，输出任务执行时间
     *
     * @return 执行结果字符串
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
        return "Hello, world";
    }
}
