package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * 建筑物状态类，对进入其中的人进行统计，并且控制建筑物中的人数
 * Author: 王俊超
 * Date: 2014-11-25
 * Time: 07:51
 * Declaration: All Rights Reserved !!!
 */
public class BuildStats {
    /**
     * 建筑物中的人数
     */
    private long numPeople;

    /**
     * 模拟人进入到建筑物中
     */
    public void comeIn() {
        System.out.printf("%s: A person enters.\n", Thread.currentThread().getName());
        synchronized (this) {
            numPeople++;
        }
        generateCard();
    }

    /**
     * 模拟人从建筑物中走出来
     */
    public void goOut() {
        System.out.printf("%s: A person leaves.\n", Thread.currentThread().getName());
        synchronized (this) {
            numPeople--;
        }
        generateReport();
    }

    /**
     * 当人进入建筑物中时，会模拟产生一张卡
     */
    private void generateCard() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当人离开建筑物时，会模拟产生一个报告
     */
    private void generateReport() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输入建筑物中的人数
     */
    public void printStats() {
        System.out.printf("%d persons in the building.\n", numPeople);
    }
}
