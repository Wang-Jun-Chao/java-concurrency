package com.concurrency.core;

import com.concurrency.task.Cinema;
import com.concurrency.task.TicketOffice1;
import com.concurrency.task.TicketOffice2;

public class Main {
    public static void main(String[] args) {
        // 创建一个电影院对象
        Cinema cinema = new Cinema();

        // 创建一个出售一号影院厅票的售票窗口对象，并且让其在一个线程中运行
        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");

        // 创建一个出售二号影院厅票的售票窗口对象，并且让其在一个线程中运行
        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");

        // 启动两个售票窗口线程
        thread1.start();
        thread2.start();

        try {
            // 等待两个线程完成
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出电影院剩余的票数
        System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());
    }
}
