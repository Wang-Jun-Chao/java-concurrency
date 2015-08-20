package com.concurrency.utils;

import java.util.Random;

/**
 * 矩阵模拟类，随机生成0-9之间数字二维矩
 */
public class MatrixMock {
    /**
     * 0-9之间数字二维矩阵
     */
    private int[][] data;

    /**
     * 构造函数
     *
     * @param size   矩阵的行数
     * @param length 每行的长度
     * @param number 要查找的数字
     */
    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }

        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", counter, number);
    }

    /**
     * 获取行row行数据
     *
     * @param row 行数
     * @return 第row行数据，没有就返回null
     */
    public int[] getRow(int row) {
        if (row >= 0 && row < data.length) {
            return data[row];
        }

        return null;
    }
}

