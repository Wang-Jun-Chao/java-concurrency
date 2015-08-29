package com.concurrency.task;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义抽象队列同步器
 */
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

    private static final long serialVersionUID = 1L;

    // 原子变量，存储锁的状态，0闲，1忙
    private AtomicInteger state;

    /**
     * 构造函数
     */
    public MyAbstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    /**
     * 获取锁
     *
     * @param arg （在本方法中不使用）
     * @return true获取，false未获取
     */
    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0, 1);
    }

    /**
     * 释放锁
     *
     * @param arg 释放量（本方法中不使用）
     * @return true成功，false失败
     */
    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1, 0);
    }
}
