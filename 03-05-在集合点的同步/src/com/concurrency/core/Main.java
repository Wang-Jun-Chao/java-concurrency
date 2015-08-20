package com.concurrency.core;

import com.concurrency.task.Grouper;
import com.concurrency.task.Searcher;
import com.concurrency.utils.MatrixMock;
import com.concurrency.utils.Results;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {

        final int ROWS = 10000; // 矩阵的行数
        final int NUMBERS = 1000; // 矩阵的列数
        final int SEARCH = 5; // 要查询的数字
        final int PARTICIPANTS = 5; // 查询线程的个数
        final int LINES_PARTICIPANT = 2000; // 每个查询线程处理的行数
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH); // 创建矩阵模拟对象
        Results results = new Results(ROWS); // 创建结果对象
        Grouper grouper = new Grouper(results); // 创建组合对象

        // 创建一个同步栅对象，它有5个参与者， 5个参与者线程完成后，会调用grouper中的run方法
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        // 创建5个参与者对象，并且让它们各自在单独的线程中运行
        Searcher[] searchers = new Searcher[PARTICIPANTS];
        for (int i = 0; i < searchers.length; i++) {
            searchers[i] = new Searcher(barrier, i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT,
                    mock, results, PARTICIPANTS);

            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}
