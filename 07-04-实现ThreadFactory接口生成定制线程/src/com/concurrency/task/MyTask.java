package com.concurrency.task;

import java.util.concurrent.TimeUnit;


/**
 * 自定义的任务类
 */
public class MyTask implements Runnable {

	/**
	 * 主方法，休眠两秒钟
	 */
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
