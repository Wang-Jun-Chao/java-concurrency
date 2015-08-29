package com.concurrency.task;

/**
 * 自定义任务类
 */
public class Task extends MyWorkerTask {
    private static final long serialVersionUID = 1L;

    // 任务要处理的数组
    private int array[];

    // 开始做处理的位置
    private int start;

    // 处理结束的位置（不包含）
    private int end;

    /**
     * 构造函数，初始化属性
     *
     * @param name  任务的名称
     * @param array 处理的数组
     * @param start 开始处理的位置
     * @param end   处理结束的位置
     */
    public Task(String name, int array[], int start, int end) {
        super(name);
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * 主访方法，如果要处理的元素个娄大于100个就分成两个子任务进行处理
     */
    @Override
    protected void compute() {
        if (end - start > 100) {
            int mid = (end + start) / 2;
            Task task1 = new Task(this.getName() + "1", array, start, mid);
            Task task2 = new Task(this.getName() + "2", array, mid, end);
            invokeAll(task1, task2);
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
