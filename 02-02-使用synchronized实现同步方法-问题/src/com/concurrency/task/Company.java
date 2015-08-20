package com.concurrency.task;

public class Company implements Runnable {

    /**
     * 一个帐户
     */
    private Account account;

    /**
     * 构造函数
     * @param account 帐户对象
     */
    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.account.addAccount(1000);
        }
    }
}
