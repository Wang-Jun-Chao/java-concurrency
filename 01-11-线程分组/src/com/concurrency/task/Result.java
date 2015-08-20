package com.concurrency.task;

/**
 * 结果类用于存储搜索结果
 */
public class Result {
    /**
     * 完成任务的线程名
     */
    private String name;

    /**
     * 获取完成任务的线程名
     * @return  完成任务的线程名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置完成任务的线程名
     * @param name 完成任务的线程名
     */
    public void setName(String name) {
        this.name = name;
    }
}
