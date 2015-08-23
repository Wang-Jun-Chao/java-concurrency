package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 事件类，实现了延迟队列接口
 */
public class Event implements Delayed {

    /**
     * 激活事件的时间
     */
    private Date startDate;

    /**
     * 构造函数
     *
     * @param startDate 激活事件的时间
     */
    public Event(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 比较两个事件
     */
    @Override
    public int compareTo(Delayed o) {
        long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 返回离激活时间还剩余的毫秒数
     */
    @Override
    public long getDelay(TimeUnit unit) {
        Date now = new Date();
        long diff = startDate.getTime() - now.getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

}
