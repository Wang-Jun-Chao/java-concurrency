package com.concurrency.task;

import java.util.concurrent.CountDownLatch;

/**
 * 视频会类
 * 使用倒计时闩来控制所有参与者都到达后才发生事件
 */
public class VideoConference implements Runnable {
    /**
     * 倒计时闩对象
     */
    private final CountDownLatch controller;

    /**
     * 构造函数，初始化倒计时闩
     * @param number 参与者人数
     */
    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    /**
     * 每个参与到视频会议的都会调用此方法
     * @param name 参与者
     */
    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }

    /**
     * 核心方法，当所有参与者都到达了，就开始视频仁义
     */
    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            // Wait for all the participants
            controller.await();
            // Starts the conference
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
