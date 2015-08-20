package com.concurrency.utils;

/**
 * 文件模拟类，
 */
public class FileMock {
    /**
     * 模拟文件的内容
     */
    private String[] content;
    /**
     * 当前需要处理的文件第多少行
     */
    private int index;

    /**
     * 构造函数，随机生成文件的内容
     *
     * @param size   文件的行数
     * @param length 每行文件的字符数
     */
    public FileMock(int size, int length) {
        this.content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                builder.append((char) indice);
            }
            content[i] = builder.toString();
        }
        this.index = 0;
    }

    /**
     * 判断是否还有文件的行数需要处理
     *
     * @return true是，false否
     */
    public boolean hasMoreLines() {
        return this.index < this.content.length;
    }

    /**
     * 返回下一行的文件内容
     *
     * @return 有返回文件内容，没有返回false
     */
    public String getLine() {
        if (this.hasMoreLines()) {
            System.out.println("Mock: " + (this.content.length - this.index));
            return this.content[this.index++];
        }
        return null;
    }
}
