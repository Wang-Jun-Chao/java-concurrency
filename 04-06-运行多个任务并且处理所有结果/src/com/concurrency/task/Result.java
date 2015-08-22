package com.concurrency.task;

/**
 * 结果类，存储中并发任务产生的结果
 */
public class Result {
    /**
     * 产生结果的任务的名字
     */
    private String name;
    /**
     * 产生的结果值
     */
    private int value;

    /**
     * 返回任务的名字
     *
     * @return 任务的名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设备任务的名字
     *
     * @param name 任务的名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回结果
     *
     * @return 结果值
     */
    public int getValue() {
        return value;
    }

    /**
     * 设置结果
     *
     * @param value 结果值
     */
    public void setValue(int value) {
        this.value = value;
    }
}
