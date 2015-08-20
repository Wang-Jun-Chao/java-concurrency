package com.concurrency.core;

import com.concurrency.task.Calculator;

public class Main {
    public static void main(String[] args) {
        // 创建一个执行10次的循环。在每次循环中创建一个Calculator 对象，
        // 一个Thread对象，这个Thread对象使用刚创建的Calculator对象作为构造器的参数，
        // 然后调用刚创建的Thread对象的start()方法。
        for (int i = 0; i <= 10; i++) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }
}
