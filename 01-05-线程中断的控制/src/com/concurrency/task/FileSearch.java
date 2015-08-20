package com.concurrency.task;

import java.io.File;

// 文件搜索类，给定一个文件目录，搜索其中指定的文件
public class FileSearch implements Runnable {
    /**
     * 搜索的初始路径
     */
    private String initPath;
    /**
     * 要搜索的文件名
     */
    private String fileName;


    /**
     * 构造函数
     *
     * @param initPath 搜索的初始路径
     * @param fileName 要搜索的文件名
     */
    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {

    }

    /**
     * 清空资源，在本例中为空
     */
    private void cleanResources() {
        // 不需要做什么
    }

    /**
     * 处理目录
     *
     * @param file 待处理的目录
     * @throws InterruptedException 线程被中断时抛出异常
     */
    private void directoryProcess(File file) throws InterruptedException {
        File[] list = file.listFiles();  // 获取当目录中的所有文件
        if (list != null) { // 如果当前目录下有文件
            for (int i = 0; i < list.length; i++) {  // 遍布所有文件
                if (list[i].isDirectory()) { // 如果是一个目录
                    directoryProcess(list[i]); // 递归处理
                } else {
                    fileProcess(list[i]); // 如果是一个文件，调用文件处理方法
                }
            }
        }
    }

    /**
     * 文件处理方法
     *
     * @param file 待处理的文件名
     * @throws InterruptedException 线程被中断时抛出异常
     */
    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(this.fileName)) { // 当前文件名与要查找的文件同名，就输出信息
            System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        if (Thread.interrupted()) {  // 程序被中断就抛出异常
            throw new InterruptedException();
        }
    }
}
