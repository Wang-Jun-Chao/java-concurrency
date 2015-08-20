package com.concurrency.task;

/**
 * 公司类，模拟向一个帐户存钱
 */
public class Company implements Runnable {

    /**
     * 帐户对象
     */
    private Account account;

    /**
     * 构造函数，初始化帐户属性
     *
     * @param account 帐户对象
     */
    public Company(Account account) {
        this.account = account;
    }

    /**
     * 核心方法，存钱
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.addAmount(1000);
        }
    }

}
