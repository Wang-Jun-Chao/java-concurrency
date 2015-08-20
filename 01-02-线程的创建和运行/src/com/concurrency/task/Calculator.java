package com.concurrency.task;

// 创建一个名为Calculator的类，它实现了 Runnable接口
public class Calculator implements Runnable {
    // 声明一个名为number的私有（private) int属性
    private int number;

    // 编写这个类的一个构造器，用来为属性number设置值。
    public Calculator(int number) {
        this.number = number;
    }

    // run方法。这个方法用来执行我们创建的线程的指令，它将对指定的数字进行乘法表运算。
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, number * i);
        }
    }
}
