package com.concurrency.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * This class implements a basic Lock. It uses a myAbstractQueueSyncrhonized object
 * as the element from which implement the methods of the lock.
 */
public class MyLock implements Lock {

    /**
     * Synchronizer to implement the operations of the locks
     */
    private AbstractQueuedSynchronizer sync;

    /**
     * Constructor of the class. It initializes its attribute
     */
    public MyLock() {
        sync = new MyAbstractQueuedSynchronizer();
    }

    /**
     * Method that try to acquire the lock. If it can't, the thread
     * will be blocked until the thread that has it release the lock
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * Method that try to acquire the lock. If it can't, the thread will
     * be blocked until the thread that has it release the lock. The difference
     * with the lock() method is that in this case, the blocked threads can
     * be interrupted
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * Method that try to acquire the lock. If it can, the method returns the true
     * value. It it can't, the method return the false value
     */
    @Override
    public boolean tryLock() {
        try {
            return sync.tryAcquireNanos(1, 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method that try to acquire the lock. If it can, the method returns the true value.
     * If it can't, wait the time specified as parameter and if the lock hasn't been
     * released, it returns the false value. It the lock is released in that period of time,
     * the thread acquires the lock and the method returns the true value
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
    }

    /**
     * Method that release the lock
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * Method that creates a new condition for the lock
     */
    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }

}
