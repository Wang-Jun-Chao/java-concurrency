package com.concurrency.task;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 加法器，将数组中的每元素增加指定个单位
 */
public class Incrementer implements Runnable {

	/**
	 * 要执行加法的数组
	 */
	private AtomicIntegerArray vector; 
	
	/**
	 * 构造函数
	 * @param vector 要执行加法的数组
	 */
	public Incrementer(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	/**
	 * 核心方法，将数组中的每元素增加指定个单位
	 */
	@Override
	public void run() {
		
		for (int i=0; i<vector.length(); i++){
			vector.getAndIncrement(i);
		}
		
	}

}
