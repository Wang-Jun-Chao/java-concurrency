package com.concurrency.task;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义原子类，进行停车计数
 */
public class ParkingCounter extends AtomicInteger {

    private static final long serialVersionUID = 1L;

    // 最多可以停车的数目
    private int maxNumber;

    /**
     * 构造函数
     * @param maxNumber  最多可以停车的数目
     */
    public ParkingCounter(int maxNumber) {
        set(0);
        this.maxNumber = maxNumber;
    }

    /**
     * 停车操作
     *
     * @return 停车成功，返回true，否则返回false
     */
    public boolean carIn() {
        for (; ; ) {
            int value = get();
            if (value == maxNumber) {
                System.out.printf("ParkingCounter: The parking is full.\n");
                return false;
            } else {
                int newValue = value + 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has entered.\n");
                    return true;
                }
            }
        }
    }

    /**
     * 离开操作
     * @return 成功，返回true，否则返回false
     */
    public boolean carOut() {
        for (; ; ) {
            int value = get();
            if (value == 0) {
                System.out.printf("ParkingCounter: The parking is empty.\n");
                return false;
            } else {
                int newValue = value - 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has gone out.\n");
                    return true;
                }
            }
        }
    }

}
