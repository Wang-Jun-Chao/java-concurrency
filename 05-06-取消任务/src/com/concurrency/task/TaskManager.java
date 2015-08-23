package com.concurrency.task;

import com.concurrency.utils.SearchNumberTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * 任务管理类
 */
public class TaskManager {
    /**
     * 任务列表对象
     */
    private List<ForkJoinTask<Integer>> tasks;

    /**
     * 构造函数，初始化任务值列表对象
     */
    public TaskManager(){
        tasks=new ArrayList<>();
    }

    /**
     * 在列表中添加一个新的任务
     * @param task 新的任务
     */
    public void addTask(ForkJoinTask<Integer> task){
        tasks.add(task);
    }

    /**
     * 取消队列中的指定任务
     * @param cancelTask 指定的任务
     */
    public void cancelTasks(ForkJoinTask<Integer> cancelTask){
        for (ForkJoinTask<Integer> task  :tasks) {
            if (task!=cancelTask) {
                task.cancel(true);
                ((SearchNumberTask)task).writeCancelMessage();
            }
        }
    }
}
