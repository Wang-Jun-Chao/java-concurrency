package com.concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 报告生成类，模拟生成报告。
 * Author: 王俊超
 * Date: 2014-11-30
 * Time: 15:58
 * Declaration: All Rights Reserved !!!
 */
public class ReportGenerator implements Callable<String> {
    /**
     * 报告发送者
     */
    private String sender;
    /**
     * 报告的标题
     */
    private String title;

    /**
     * 构造函数，初始化报告发送者，报告的标题
     *
     * @param sender 报告发送者
     * @param title  报告的标题
     */
    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    /**
     * 核心方法，等待一个随机时间，产生一个字符串类型的报告
     *
     * @return 字符串类型的报告
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.sender, this.title, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ret = sender + ": " + title;
        return ret;
    }
}
