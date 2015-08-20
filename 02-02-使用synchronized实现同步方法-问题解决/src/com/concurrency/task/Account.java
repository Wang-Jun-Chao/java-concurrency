package com.concurrency.task;

/**
 * 帐户类，模拟一个银行帐户
 */
public class Account {
    /**
     * 帐户余额
     */
    private double balance;

    /**
     * 获取帐户余额
     *
     * @return 帐户余额
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 设置帐户余额
     *
     * @param balance 帐户余额
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 增加帐户余额
     *
     * @param amount 增加的余额
     */
    public synchronized void addAccount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp += amount;
        balance = tmp;
    }

    /**
     * 减少帐户余额
     *
     * @param amount 减少的余额
     */
    public synchronized void subtractAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp -= amount;
        balance = tmp;
    }
}
