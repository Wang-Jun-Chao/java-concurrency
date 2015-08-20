package com.concurrency.task;

import com.concurrency.utils.Buffer;
import com.concurrency.utils.FileMock;

public class Producer implements Runnable {
    /**
     * 文件模拟对象
     */
    private FileMock mock;
    /**
     * 缓冲对象
     */
    private Buffer buffer;

    /**
     * 构造函数
     *
     * @param mock   文件模拟对象
     * @param buffer 缓冲对象
     */
    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    /**
     * 核心方法，读取文件中的数据，并且将读取到的数据插入到缓冲区
     */
    @Override
    public void run() {
        this.buffer.setPendingLines(true);
        while (this.mock.hasMoreLines()) {
            String line = this.mock.getLine();
            this.buffer.insert(line);
        }
        this.buffer.setPendingLines(false);
    }
}
