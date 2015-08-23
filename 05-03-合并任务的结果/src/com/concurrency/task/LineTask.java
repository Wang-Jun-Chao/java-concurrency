package com.concurrency.task;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 行处理类，处理指定行数和单词
 */
public class LineTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 4169105159737293155L;
    /**
     * 文档中一行数据
     */
    private String line[];

    /**
     * 行处理的起始位置
     */
    private int start;
    /**
     * 行处理的结束位置
     */
    private int end;

    /**
     * 要查找的单词
     */
    private String word;

    /**
     * 构造函数
     *
     * @param line  文档中一行数据
     * @param start 行处理的起始位置
     * @param end   行处理的结束位置
     * @param word  要查找的单词
     */
    public LineTask(String line[], int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    /**
     * 核心方法，完成单词的查找
     *
     * @return 查找范围内，单词出现的次数
     */
    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 100) { // 少于100个单词就进行统计
            result = count(line, start, end, word);
        } else { // 否则就分成两个线程进行处理
            int mid = (start + end) / 2;
            LineTask task1 = new LineTask(line, start, mid + 1, word);
            LineTask task2 = new LineTask(line, mid + 1, end, word);
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
     * 统计单词出现的次数
     *
     * @param line  待待查找的行
     * @param start 处理的开始位置
     * @param end   处理的结束位置
     * @param word  查找的单词
     * @return 单词出现的次数
     */
    private Integer count(String[] line, int start, int end, String word) {
        int counter;
        counter = 0;
        for (int i = start; i < end; i++) {
            if (line[i].equals(word)) {
                counter++;
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
