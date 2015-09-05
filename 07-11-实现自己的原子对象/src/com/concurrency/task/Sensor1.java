package com.concurrency.task;

/**
 * 场景类，模拟停车操作
 */
public class Sensor1 implements Runnable {

    // 停车场计数器
    private ParkingCounter counter;

    /**
     * 构造函数
     *
     * @param counter 停车场计数器
     */
    public Sensor1(ParkingCounter counter) {
        this.counter = counter;
    }


    /**
     * 主方法，进行停车场场景模拟
     */
    @Override
    public void run() {
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carIn();
    }

}
