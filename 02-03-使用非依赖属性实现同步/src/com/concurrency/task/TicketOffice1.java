package com.concurrency.task;

/**
 * 售票窗口类，出售1号放映厅的票
 */
public class TicketOffice1 implements Runnable {
    /**
     * 电影院对象
     */
    private Cinema cinema;

    /**
     * 构造函数
     * @param cinema 电影院对象
     */
    public TicketOffice1(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets1(3);
        cinema.sellTickets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTickets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
    }
}
