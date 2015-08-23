package com.concurrency.core;

import com.concurrency.task.Task;
import com.concurrency.utils.Product;
import com.concurrency.utils.ProductListGenerator;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 创建产品生成器对象，并且门生产10000个产品
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);

        // 创建一个任务对象
        Task task = new Task(products, 0, products.size(), 0.2);

        // 创建一个分合池
        ForkJoinPool pool = new ForkJoinPool();

        // 执行任务
        pool.execute(task);

        // 输出分合池的信息
        do {
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Paralelism: %d\n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        // 关闭分合池
        pool.shutdown();

        // 检查任务是否正常完成
        if (task.isCompletedNormally()) {
            System.out.printf("Main: The process has completed normally.\n");
        }

        // 输出价格不是12的产品
        for (Product product : products) {
            if (product.getPrice() != 12) {
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
            }
        }

        // 结束程序
        System.out.println("Main: End of the program.\n");
    }
}
