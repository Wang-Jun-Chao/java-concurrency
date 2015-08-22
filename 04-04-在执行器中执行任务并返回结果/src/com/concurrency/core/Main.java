package com.concurrency.core;

import com.concurrency.task.FactorialCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        // 创建固定大小的线程池执行器，最多可以同时执行2个线程。
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        // 创建一个存储未来象的列表，未来对象关联着任务的任务的执行，并且可以获取执行的结果
        List<Future<Integer>> resultList = new ArrayList<>();
        // 创建一个随机数生成对象
        Random random = new Random();

        // 创建10个任务并且将它们送到执行器中
        for (int i = 0; i < 10; i++) {
            // 生成[0, 10)之间的10个随机数
            Integer number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            // 调用执行器的submit()方法来提交FactorialCalculator任务给执行者。
            // 这个方法返回Future<Integer>对象来管理任务，并且最终获取它的结果。
            Future<Integer> result = executor.submit(calculator);
            // 将结果存储到队列当中
            resultList.add(result);
        }
        // 创建一个do循环来监控执行者的状态，等待10个线程都完成任务。
        do {
            // 首先，写入信息到控制台，使用执行器的getCompletedTaskNumber()方法获得的已完成的任务数。
            System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
            // 然后，对于队列中的10个Future对象，使用isDone()方法，将信息写入（到控制台）表明它们所管理的任务是否已经完成
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n", i, result.isDone());
            }

            try {
                // 主线程休眠50毫秒
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 如果执行器中的已完成任务数小于10，重复这个循环。
        } while (executor.getCompletedTaskCount() < resultList.size());

        // 将获得的每个任务的结果写入控制台。对于每个Future对象，通过它的任务使用get()方法获取返回的Integer对象。
        System.out.printf("Main: Results\n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            // 在控制台输出结果
            System.out.printf("Core: Task %d: %d\n", i, number);
        }

        // 最后，调用执行器的shutdown()方法来结束这个执行器。
        executor.shutdown();

    }
}
