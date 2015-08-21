package com.concurrency.task;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 生产者类
 */
public class Producer implements Runnable {
    /**
     * 生产者生产数据后存储的地方，也是与消费者交换数据的地方
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
    public Producer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    /**
     * 核心方法，产生100个事件，分10次产生，每次产生10个事件，每个产生10个事件后，
     * 调用数据交换对象去同步消费者。生产者将存放10个事件的缓存对象发送给消费者，
     * 并且从消费者那里接收到一个空的缓存对象
     */
    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer: Cycle %d\n", cycle);
            // 生产10个事件
            for (int j = 0; j < 10; j++) {
                String message = "Event " + ((i * 10) + j);
                System.out.printf("Producer: %s\n", message);
                buffer.add(message);
            }

            try {
                // 与消费者交换数据
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Producer: %d\n", buffer.size());

            cycle++;
        }
    }
}
