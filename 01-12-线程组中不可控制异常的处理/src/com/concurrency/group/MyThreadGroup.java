package com.concurrency.group;

public class MyThreadGroup extends ThreadGroup {
    /**
     * 构造函数
     *
     * @param name 线程组名称
     */
    public MyThreadGroup(String name) {
        super(name);
    }

    /**
     * 重写未捕获的异常方法
     *
     * @param t 抛出异常的信息
     * @param e 抛出的异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 打印线程的名称
        System.out.printf("The thread %s has thrown an Exception\n", t.getId());
        // 输出异常栈信息
        e.printStackTrace(System.out);
        // 中断线程组中其余的线程
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();
    }
}
