package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 休眠两秒的任务类
 */
public class SleepTwoSecondsTask implements Callable<String> {

    /**
     * 主方法，返回休眠两秒后的时间字符串
     */
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }

}
