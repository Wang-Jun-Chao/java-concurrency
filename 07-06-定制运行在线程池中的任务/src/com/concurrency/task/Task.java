package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * 自定义任务类
 */
public class Task implements Runnable {

	/**
	 * 主方法，运行两秒钟
	 */
	@Override
	public void run() {
		System.out.printf("Task: Begin.\n");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task: End.\n");
	}
}
