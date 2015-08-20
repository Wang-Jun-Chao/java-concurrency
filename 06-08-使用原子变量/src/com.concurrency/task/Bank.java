package com.concurrency.task;

/**
 * 银行帐户类，模拟从一个帐户上取钱
 */
public class Bank implements Runnable {

    /**
     * 帐户对象
     */
    private Account account;

    /**
     * 构造函数，初始化帐户属性
     *
     * @param account 帐户对象
     */
    public Bank(Account account) {
        this.account = account;
    }


    /**
     * 核心方法，取钱
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.subtractAmount(1000);
        }
    }

}
