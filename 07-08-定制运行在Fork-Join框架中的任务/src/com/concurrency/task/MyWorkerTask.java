package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * 自定义工作任务，无返回值
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

    private static final long serialVersionUID = 1L;

    // 任务名称
    private String name;

    /**
     * 构造函数，初始化属性
     *
     * @param name 任务名称
     */
    public MyWorkerTask(String name) {
        this.name = name;
    }

    /**
     * 获取执行结果，（此任务执行无结果返回）
     */
    @Override
    public Void getRawResult() {
        return null;
    }

    /**
     * 设置结果
     */
    @Override
    protected void setRawResult(Void value) {

    }

    /**
     * 主方法，执行任务
     */
    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
        return true;
    }

    /**
     * 获取任务的名字
     *
     * @return 任务的名字
     */
    public String getName() {
        return name;
    }

    /**
     * 模板方法，等待子类实现
     */
    protected abstract void compute();
}
