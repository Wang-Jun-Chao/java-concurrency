package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 文件定时类，每隔一秒钟将实际的时间输出
public class FileClock implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());
            try {
                // 休眠一秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // 当线程被中断时，释放或者关闭线程正在使用的资源。
                System.out.printf("The FileClock has been interrupted");
                return; // 发生异常就跳出
            }
        }
    }
}
