package com.concurrency.task;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  自定义线程优先级类
 *
 * @param <E> 泛型参数
 */
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

    private static final long serialVersionUID = 1L;

    // 等待的消费者数目
    private AtomicInteger counter;

    // 存储传输元素的队列
    private LinkedBlockingQueue<E> transfered;

    // 锁对象
    private ReentrantLock lock;

    /**
     * 构造函数
     */
    public MyPriorityTransferQueue() {
        counter = new AtomicInteger(0);
        lock = new ReentrantLock();
        transfered = new LinkedBlockingQueue<>();
    }

    /**
     * 将元素发送到一个正在等待的消费者，如查没有等待中的消费者，就返回false
     */
    @Override
    public boolean tryTransfer(E e) {
        lock.lock();
        boolean value;
        if (counter.get() == 0) {
            value = false;
        } else {
            put(e);
            value = true;
        }
        lock.unlock();
        return value;
    }

    /**
     * 将元素发送到一个正在等待的消费者，如查没有等待中的消费者，将元素存储到transfer队列中，
     * 并且等待出现试图获取元素的第一个消费者，这在这前线程被阻塞
     */
    @Override
    public void transfer(E e) throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            put(e);
            lock.unlock();
        } else {
            transfered.add(e);
            lock.unlock();
            synchronized (e) {
                e.wait();
            }
        }
    }

    /**
     * 第一个参数用以表示生产和消费的元素, 第二个参数表示如果没有消费者则等待一个消费者的时间，
     * 第三个参数表示等待时间的单 位。如果有消费者在等待，它就立即发送元素。否则，将参数指定的
     * 时间转换为毫秒并使 用wait()方法让线程休眠。当消费者取元素时，如果线程仍在wait()方法中
     * 休眠，将使用notify()方法去唤醒它
     */
    @Override
    public boolean tryTransfer(E e, long timeout, TimeUnit unit)
            throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            put(e);
            lock.unlock();
            return true;
        } else {
            transfered.add(e);
            long newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
            lock.unlock();
            e.wait(newTimeout);
            lock.lock();
            if (transfered.contains(e)) {
                transfered.remove(e);
                lock.unlock();
                return false;
            } else {
                lock.unlock();
                return true;
            }
        }
    }


    /**
     * 使用counter属性的值来计算该方法的返回值，不为0返回true，否则返回false
     */
    @Override
    public boolean hasWaitingConsumer() {
        return (counter.get() != 0);
    }

    /**
     * 返回 counter 属性的值
     */
    @Override
    public int getWaitingConsumerCount() {
        return counter.get();
    }

    /**
     * 如果在transfered队列中没有元素，则释放锁并尝试使用toke()方法从队列中取
     * 得一个元素并再次获取锁.如果队列中没有元素，该方法将让线程休眠直至有元素可被消费
     */
    @Override
    public E take() throws InterruptedException {
        lock.lock();
        counter.incrementAndGet();
        E value = transfered.poll();
        if (value == null) {
            lock.unlock();
            value = super.take();
            lock.lock();
        } else {
            synchronized (value) {
                value.notify();
            }
        }
        counter.decrementAndGet();
        lock.unlock();
        return value;
    }
}
