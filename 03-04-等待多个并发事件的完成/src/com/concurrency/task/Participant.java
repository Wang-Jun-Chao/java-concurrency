package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * 参与者类
 */
public class Participant implements Runnable {
    /**
     * 视频会议对象
     */
    private VideoConference conference;
    /**
     * 参与者的名称（仅仅是为了记录使用）
     */
    private String name;

    /**
     * 构造函数
     *
     * @param conference 视频会议对象
     * @param name       参与者的名称
     */
    public Participant(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }


    /**
     * 核心方法，等待一个随机时间就进入视频会议
     */
    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
}
