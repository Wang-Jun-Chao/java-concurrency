package com.concurrency.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Author: 王俊超
 * Date: 2014-11-30
 * Time: 19:29
 * Declaration: All Rights Reserved !!!
 */
public class DocumentTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = -1257254196502539272L;
    /**
     * 等待处理的文档
     */
    private String document[][];
    /**
     * 文档处理的开始行
     */
    private int start;
    /**
     * 文档处理的结束行
     */
    private int end;

    /**
     * 要查找的单词
     */
    private String word;

    /**
     * 构造函数
     *
     * @param document 等待处理的文档
     * @param start    文档处理的开始行
     * @param end      文档处理的结束行
     * @param word     要查找的单词
     */
    public DocumentTask(String document[][], int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }


    /**
     * 核心方法，统计文档的中指定的单词
     *
     * @return 指定的单词出现的次数
     */
    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 10) {  // 少于10行，使用行处理方法
            result = processLines(document, start, end, word);
        } else { // 否则使用两个线程去处理
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 返回number1+number2的和
     *
     * @param number1 加数l
     * @param number2 加数2
     * @return 和
     */
    private Integer groupResults(Integer number1, Integer number2) {
        return number1 + number2;
    }

    /**
     * 行处理方法
     *
     * @param document 等待处理的文档
     * @param start    文档处理的开始行
     * @param end      文档处理的结束行
     * @param word     要查找的单词
     * @return 指定的单词出现的次数
     */
    private Integer processLines(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = new ArrayList<LineTask>();

        // 有多少行就创建多少个行处理任务对象
        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        invokeAll(tasks);

        // 统计结果
        int result = 0;
        for (LineTask task : tasks) {
            try {
                result = result + task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
