package com.concurrency.task;

/**
 * 写者类，产生价格
 */
public class Writer implements Runnable {
    /**
     * 价格信息对象
     */
    private PricesInfo pricesInfo;

    /**
     * 构造函数
     *
     * @param pricesInfo 价格信息对象
     */
    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    /**
     * 核心方法，写价格
     */
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
