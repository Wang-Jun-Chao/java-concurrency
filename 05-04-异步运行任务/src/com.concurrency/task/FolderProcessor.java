package com.concurrency.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 文件夹处理类，查找指定文件夹及其子文件夹下的指定后缀名的文件
 * Author: 王俊超
 * Date: 2014-12-01
 * Time: 08:27
 * Declaration: All Rights Reserved !!!
 */
public class FolderProcessor extends RecursiveTask<List<String>> {
    private static final long serialVersionUID = -6119741136325003142L;
    /**
     * 开始处理的文件目录
     */
    private String path;

    /**
     * 要查找的文件后缀名
     */
    private String extension;

    /**
     * 构造函数
     *
     * @param path      开始处理的文件目录
     * @param extension 要查找的文件后缀名
     */
    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    /**
     * 核心方法，查找文件夹下所有指定后缀的文件，如果是一个文件夹就开创建子线程去运行
     *
     * @return 查找到的文件集合
     */
    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();
        File file = new File(path);
        File content[] = file.listFiles();
        if (content != null) {
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                } else {
                    if (checkFile(content[i].getName())) {
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }

            if (tasks.size() > 50) {
                System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
            }
            addResultsFromTasks(list, tasks);
        }

        return list;
    }

    /**
     * 汇总统计结果
     *
     * @param list  结果存放的集合
     * @param tasks 任务集合
     */
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor item : tasks) {
            list.addAll(item.join());
        }
    }

    /**
     * 检查文件是否以指定的名称结束
     *
     * @param name 文件扩展名
     * @return true以指定的名字结束，false不以指定的名字结束
     */
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
