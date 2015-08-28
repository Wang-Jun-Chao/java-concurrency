package com.concurrency.core;

import com.concurrency.task.Account;
import com.concurrency.task.Bank;
import com.concurrency.task.Company;

public class Main {
    public static void main(String[] args) {
        // 创建一个帐户对象
        Account account = new Account();
        // 初始化帐户余额
        account.setBalance(1000);

        // 创建一个公司对象，并且将公司对象放到线程中去运行
        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        // 创建一个银行对象，并且将银行对象放到线程中去运行
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        // 输出帐户对象最初的信息
        System.out.printf("Account : Initial Balance: %d\n", account.getBalance());

        // 启动线程
        companyThread.start();
        bankThread.start();

        try {
            // 等待线程完成
            companyThread.join();
            bankThread.join();
            // 输出余额
            System.out.printf("Account : Final Balance: %d\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
