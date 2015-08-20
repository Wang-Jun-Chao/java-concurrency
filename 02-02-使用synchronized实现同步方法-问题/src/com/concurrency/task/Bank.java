package com.concurrency.task;

public class Bank implements Runnable {
    /**
     * 一个帐户
     */
    private Account account;

    /**
     * 构造函数
     *
     * @param account 银行帐户
     */
    public Bank(Account account) {
        this.account = account;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.account.subtractAmount(1000);
        }
    }
}
