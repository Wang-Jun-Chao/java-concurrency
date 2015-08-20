package com.concurrency.event;

import java.util.Date;

/**
 * 事件类，存储事件信息
 */
public class Event {
    /**
     * 事件日期
     */
    private Date date;
    /**
     * 事件信息
     */
    private String event;

    /**
     * 获取事件日期
     *
     * @return 事件日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置事件日期
     *
     * @param date 事件日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取事件信息
     *
     * @return 事件信息
     */
    public String getEvent() {
        return event;
    }

    /**
     * 设置事件信息
     *
     * @param event 事件信息
     */
    public void setEvent(String event) {
        this.event = event;
    }
}
