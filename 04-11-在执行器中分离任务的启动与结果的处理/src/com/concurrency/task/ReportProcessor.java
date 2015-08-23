package com.concurrency.task;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 报表处理器类，通过CompletionService对象处理报表生成器的结果
 */
public class ReportProcessor implements Runnable {
    /**
     * 完成服务对象
     */
    private CompletionService<String> service;
    /**
     * 任务完成的标记
     */
    private boolean end;

    /**
     * 构造函数，初始化完成服务对象
     *
     * @param service 完成服务对象
     */
    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
    }


    /**
     * 核心方法，如果任务没有完成就一直执行
     */
    @Override
    public void run() {
        while (!end) {
            try {
                // 调用CompletionService接口的poll()方法，获取CompletionService执行的下个已完成任务的Future对象
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if (result != null) {
                    // 使用Future对象的get()方法获取任务的结果，并且将这些结果写入到控制台
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received: %s\n", report);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("ReportSender: End\n");
    }

    /**
     * 设置任务完成标记
     * @param end  任务完成标记
     */
    public void setEnd(boolean end) {
        this.end = end;
    }
}
