package com.concurrency.task;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 消费者类
 * Author: 王俊超
 * Date: 2014-11-26
 * Time: 09:05
 * Declaration: All Rights Reserved !!!
 */
public class Consumer implements Runnable {

    /**
     * 消费者消费数据的地方，也是与消费者交换数据的地方
     */
    private List<String> buffer;
    /**
     * 同步生产者与消息者交换数据的交换对象
     */
    private final Exchanger<List<String>> exchanger;

    /**
     * 构造函数，初始化属性
     *
     * @param exchanger 数据的交换对象
     * @param buffer    数据存储对象
     */
    public Consumer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    /**
     * 核心方法，它消费生产者产生的事件，每消费10个事件后，它使用交换对象去同步生产者。
     * 它将已经消费完的空缓存对象发送给生产者，同时获取生产者生产的装有10个事件的缓存对象
     */
    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                // 等待生产的数据，并且将空的缓存对象发送给生产者
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Consumer: %d\n", buffer.size());

            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("Consumer: %s\n", message);
                buffer.remove(0);
            }

            cycle++;
        }
    }
}
