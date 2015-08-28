package com.concurrency.core;

import com.concurrency.task.MyTask;
import com.concurrency.task.MyThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 创建一个自定义的线程工厂类
		MyThreadFactory threadFactory=new MyThreadFactory("MyThreadFactory");
		
		// 创建一个线程缓冲池执行器对象，它的参数是线程工厂类
		ExecutorService executor=Executors.newCachedThreadPool(threadFactory);
		
		// 创建一个自定义任务
		MyTask task=new MyTask();
		
		// 任务提交到执行器
		executor.submit(task);
		
		// 关闭执行器对象
		executor.shutdown();
		
		// 等待执行器中的任务运行结束
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		// 输出信息表明程序已经结束
		System.out.printf("Main: End of the program.\n");
	}
}
