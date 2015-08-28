package com.concurrency.task;

import java.util.Date;

/**
 * 自定义线程类
 */
public class MyThread extends Thread {


	// 线程创建的时间
	private Date creationDate;

	// 线开始执行的时间
	private Date startDate;

	// 线程完成执行的时间
	private Date finishDate;

	/**
	 * 构造函数
	 *
	 * @param target 执行的任务
	 * @param name   线程的名称
	 */
	public MyThread(Runnable target, String name) {
		super(target, name);
		setCreationDate();
	}

	/**
	 * 主方法，记录线程运行的开始和结束时间
	 */
	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
	}

	/**
	 * 设置线程创建的时间
	 */
	public void setCreationDate() {
		creationDate = new Date();
	}

	/**
	 * 设置线程开始执行的时间
	 */
	public void setStartDate() {
		startDate = new Date();
	}

	/**
	 * 设置线程结束执行的时间
	 */
	public void setFinishDate() {
		finishDate = new Date();
	}

	/**
	 * 计算线程执行的时间
	 *
	 * @return 线程执行的时间
	 */
	public long getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}

	/**
	 * 输出线程相关信息
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Creation Date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}
}
