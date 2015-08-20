package com.concurrency.core;

import com.concurrency.task.PricesInfo;
import com.concurrency.task.Reader;
import com.concurrency.task.Writer;

public class Main {
    public static void main(String[] args) {
        // 创建价格信息对象，用于存储价格
        PricesInfo pricesInfo = new PricesInfo();

        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];

        // 创建5个读者并且将其放在不同的线程中远行
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        // 创建一个写者，并且将其放在一个线程中运行
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        // 启动读者写者线程
        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        threadWriter.start();
    }
}
