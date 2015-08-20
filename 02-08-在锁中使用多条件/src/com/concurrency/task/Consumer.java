package com.concurrency.task;

import com.concurrency.utils.Buffer;

import java.util.Random;

public class Consumer implements Runnable {
    /**
     * 缓冲对象
     */
    private Buffer buffer;

    /**
     * 构造函数
     *
     * @param buffer 缓冲对象
     */
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * 核心方法，当缓冲中有数据时就进行处理
     */
    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    /**
     * 模拟处理一行数据，休眠[0,100)毫秒
     *
     * @param line 一行数据
     */
    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
