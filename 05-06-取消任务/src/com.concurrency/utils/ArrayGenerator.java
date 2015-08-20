package com.concurrency.utils;

import java.util.Random;

/**
 * 整形数组生成类
 * Author: 王俊超
 * Date: 2014-12-01
 * Time: 09:10
 * Declaration: All Rights Reserved !!!
 */
public class ArrayGenerator {
    /**
     * 生成整形数组，生成的值在[0, size)
     * @param size 数组长度
     * @return 长度为size的数组
     */
    public int[] generateArray(int size) {
        int array[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }
}
