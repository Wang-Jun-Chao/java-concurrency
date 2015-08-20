package com.concurrency.task;

import java.util.concurrent.CompletionService;

/**
 * 报告请求类
 * Author: 王俊超
 * Date: 2014-11-30
 * Time: 15:57
 * Declaration: All Rights Reserved !!!
 */
public class ReportRequest implements Runnable {
    /**
     * 报告请求类的名字
     */
    private String name;
    /**
     * 完成任务对象
     */
    private CompletionService<String> service;

    /**
     * 构造函数，初始化报告请求类的名字，完成任务对象
     *
     * @param name    报告请求类的名字
     * @param service 完成任务对象
     */
    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    /**
     * 核心方法，创建一个报告生成对象，并且将其提交到完成任务对象
     */
    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
        service.submit(reportGenerator);
    }
}
