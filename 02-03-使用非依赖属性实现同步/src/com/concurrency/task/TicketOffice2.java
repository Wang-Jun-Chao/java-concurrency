package com.concurrency.task;

/**
 * 售票窗口类，出售2号放映厅的票
 */
public class TicketOffice2 implements Runnable {
    /**
     * 电影院对象
     */
    private Cinema cinema;

    /**
     * 构造函数
     *
     * @param cinema 电影院对象
     */
    public TicketOffice2(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets2(2);
        cinema.sellTickets2(4);
        cinema.sellTickets1(2);
        cinema.sellTickets1(1);
        cinema.returnTickets2(2);
        cinema.sellTickets1(3);
        cinema.sellTickets2(2);
        cinema.sellTickets1(2);
    }
}
