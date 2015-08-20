package com.concurrency.task;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 帐户类
 */
public class Account {

    /**
     * 帐户余额
     */
    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    /**
     */
    /**
     * 获取帐户余额
     *
     * @return 帐户余额
     */
    public long getBalance() {
        return balance.get();
    }

    /**
     * 设置帐户余额
     *
     * @param balance 帐户余额
     */
    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    /**
     * 增加余额
     *
     * @param amount 增加的数目
     */
    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }

    /**
     * 减少余额
     *
     * @param amount 减少的数目
     */
    public void subtractAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }

}
