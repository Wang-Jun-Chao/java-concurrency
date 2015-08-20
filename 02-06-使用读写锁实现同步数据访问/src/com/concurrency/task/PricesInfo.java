package com.concurrency.task;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 价格信息类，这个类存储了两个价格，一个写者写这个价格，多个读者读这个价格
 */
public class PricesInfo {
    /**
     * 两个价格g
     */
    private double price1;
    private double price2;

    /**
     * 控制价格访问的锁
     */
    private ReadWriteLock lock;

    /**
     * 构造函数，初始化价格和锁
     */
    public PricesInfo() {
        this.price1 = 1.0;
        this.price2 = 2.0;
        this.lock = new ReentrantReadWriteLock();
    }

    /**
     * 获取第一个价格
     *
     * @return 第一个价格
     */
    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    /**
     * 获取第二个价格
     *
     * @return 第二个价格
     */
    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    /**
     * 设置两个价格
     *
     * @param price1 第一个价格
     * @param price2 第二个价格
     */
    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }
}
