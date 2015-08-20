package com.concurrency.task;

/**
 * Author: 王俊超
 * Date: 2014-11-25
 * Time: 07:59
 * Declaration: All Rights Reserved !!!
 */
public class Sensor2 implements Runnable {
    /**
     * 建筑物状态对象
     */
    private BuildStats stats;

    /**
     * 构造函数
     *
     * @param stats 建筑物状态对象
     */
    public Sensor2(BuildStats stats) {
        this.stats = stats;
    }

    /**
     * 核心方法，模拟人在建筑物中的进出
     */
    @Override
    public void run() {
        stats.comeIn();
        stats.comeIn();
        stats.goOut();
        stats.goOut();
        stats.goOut();
    }
}
