package com.concurrency.task;

/**
 * 读者类，消费价格
 */
public class Reader implements Runnable {
    /**
     * 价格信息对象
     */
    private PricesInfo pricesInfo;

    /**
     * 构造函数
     *
     * @param pricesInfo 价格信息对象
     */
    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    /**
     * 核心方法，消费两个价格，并且将他们输出
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
