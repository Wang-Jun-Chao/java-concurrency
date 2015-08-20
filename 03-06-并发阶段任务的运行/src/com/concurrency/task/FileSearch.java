package com.concurrency.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 文件查找类，它将在一个文件夹及子文件夹中查找过去24小时内修改过的指定扩展名的文件
 */
public class FileSearch implements Runnable {
    /**
     * 用于查找的文件夹
     */
    private String initPath;
    /**
     * 要查找的文件的扩展名
     */
    private String end;
    /**
     * 查找到的文件的完整路径
     */
    private List<String> results;
    /**
     * 阶段对象，用来控制任务不同阶段的同步
     */
    private Phaser phaser;

    /**
     * 构造函数，初始化声明的属性
     *
     * @param initPath 用于查找的文件夹
     * @param end      要查找的文件的扩展名
     * @param phaser   阶段对象
     */
    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        this.results = new ArrayList<>();
    }

    /**
     * 核心方法
     */
    @Override
    public void run() {
        // 等待所有文件对象被创建
        this.phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

        // 第一个阶段：查找所有的文件
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }

        // 如果没有找到结果，就从这个阶段对象中注销，并且退出程序
        if (!checkResults()) {
            return;
        }

        // 第二阶段：过滤查找到的结果
        filterResults();

        // 如果过滤后没有结果，就从这个阶段对象中注销，并且退出程序
        if (!checkResults()) {
            return;
        }

        // 第三阶段：显示查找信息
        showInfo();
        // 通知Phaser对象，当前线程已经结束这个阶段，并且将不再参与接下来的阶段操作
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());

    }

    /**
     * 将结果集中的元素打印到控制台
     */
    private void showInfo() {
        for (String result : this.results) {
            File file = new File(result);
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        // 通知Phaser对象，当前线程已经完成了当前阶段，需要被阻塞直到其它线程都完成当前阶段
        this.phaser.arriveAndAwaitAdvance();
    }

    /**
     * 检查结果集是否为空，如果结果集为空就从阶段对象中注销，否则等待其它的线程完成同样的的任务阶段
     *
     * @return false结果集为空，true结果集不为空
     */
    private boolean checkResults() {
        if (this.results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
            // 通知Phaser对象，当前线程已经结束这个阶段，并且将不再参与接下来的阶段操作
            this.phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            // 通知Phaser对象，当前线程已经完成了当前阶段，需要被阻塞直到其它线程都完成当前阶段
            this.phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    /**
     * 文件过滤方法，将不是24小时前修改过的文件删除
     */
    private void filterResults() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for (String result : results) {
            File file = new File(result);
            long fileDate = file.lastModified();
            // 表明修改是在24小时前发生的
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(result);
            }
        }
        results = newResults;
    }

    /**
     * 目录处理方法，处理file目录下的所有文件夹和文件
     *
     * @param file 开始处理的文件
     */
    private void directoryProcess(File file) {

        // 获取file目录下的所有文件和目录
        File list[] = file.listFiles();
        if (list != null) {
            for (File aFile : list) {
                if (aFile.isDirectory()) {
                    // 如果是目录就递归处理它
                    directoryProcess(aFile);
                } else {
                    // 如果是一个文件，就调用文件处理方法
                    fileProcess(aFile);
                }
            }
        }
    }

    /**
     * 文件处理方法
     *
     * @param file 文件对象
     */
    private void fileProcess(File file) {
        // 如果文件以指定的后缀点结束，就将文件的绝对路径保存到结果集合中
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }
}
