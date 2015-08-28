package com.concurrency.task;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂类
 */
public class MyThreadFactory implements ThreadFactory {

	// 记录工厂中的线程数目
	private int counter;

	// 工厂中线程的名称前缀
	private String prefix;

	/**
	 * 构造函数
	 *
	 * @param prefix 工厂中线程名称前缀
	 */
	public MyThreadFactory(String prefix) {
		this.prefix = prefix;
		counter = 1;
	}

	/**
	 * 创建线程的工厂
	 */
	@Override
	public Thread newThread(Runnable r) {
		MyThread myThread = new MyThread(r, prefix + "-" + counter);
		counter++;
		return myThread;
	}
}
