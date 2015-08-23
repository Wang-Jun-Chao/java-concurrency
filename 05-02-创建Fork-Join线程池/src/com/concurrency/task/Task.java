package com.concurrency.task;

import com.concurrency.utils.Product;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 *任务执行类，如果产品多于10个就让分出子任务进行处理
 */
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 6876633274768462482L;

    /**
     * 产品集合对象
     */
    private List<Product> products;
    /**
     * 处理的第一个产品位置
     */
    private int first;
    /**
     * 处理的最后一个产品位置
     */
    private int last;
    /**
     * 价格增长率
     */
    private double increment;

    /**
     * 构造函数，初始化属性
     *
     * @param products  产品集合对象
     * @param first     处理的第一个产品位置
     * @param last      处理的最后一个产品位置（不包含）
     * @param increment 价格增长率
     */
    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    /**
     * 对产品进行计算
     */
    @Override
    protected void compute() {
        if (last - first < 10) {  // 处理的产品数少于10个就提价
            updatePrices();
        } else {  // 否则就让两个子线程去执行
            int middle = (first + last) / 2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }
    }

    /**
     * 更价格，将指定的范围内的产品提价
     */
    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));  // 按increment比率提价
        }
    }
}
