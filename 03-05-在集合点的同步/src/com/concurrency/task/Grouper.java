package com.concurrency.task;

import com.concurrency.utils.Results;

/**
 * 组合类，汇总查找的结果
 */
public class Grouper implements Runnable {
    /**
     * 结果对象
     */
    private Results results;

    /**
     * 构造函数
     *
     * @param results 结果对象
     */
    public Grouper(Results results) {
        this.results = results;
    }

    /**
     * 核心方法，对查找的结果进行汇总
     */
    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = results.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
