package com.concurrency.core;

import com.concurrency.task.Account;
import com.concurrency.task.Bank;
import com.concurrency.task.Company;

public class Main {
    public static void main(String[] args) {
        // 创建一个帐户对象
        Account account = new Account();
        // 帐户初始值为1000
        account.setBalance(1000);

        // 创建一个公司对象，并且让公司对象在一个线程中运行
        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        // 创建一个银行对象，并且让银行对象在一个线程中运行
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        // 输出初始的余额
        System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

        // 启动公司和银行两个线程
        companyThread.start();
        bankThread.start();

        try {
            // 等待两个线程的完成
            companyThread.join();
            bankThread.join();
            // 输出最后的余额
            System.out.printf("Account : Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
