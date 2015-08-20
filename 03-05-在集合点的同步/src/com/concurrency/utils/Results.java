package com.concurrency.utils;

/**
 * 结果类，保存矩阵中每行找到指定数字的次数
 */
public class Results {
    /**
     * 保存矩阵中每行找到指定数字的次数
     */
    private int[] data;

    /**
     * 构造函数
     *
     * @param size 数组长度
     */
    public Results(int size) {
        this.data = new int[size];
    }

    /**
     * 设置数组的值
     *
     * @param position 位置
     * @param value    要设置的值
     */
    public void setData(int position, int value) {
        data[position] = value;
    }

    /**
     * 获取保存的数据
     *
     * @return 保存的数据
     */
    public int[] getData() {
        return data;
    }
}
