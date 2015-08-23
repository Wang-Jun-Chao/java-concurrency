package com.concurrency.utils;

/**
 * 产品类，保存产品的名称和价格
 */
public class Product {
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private double price;

    /**
     * 获取产品名称
     *
     * @return 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产名品名称
     *
     * @param name 产名名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取产品价格
     *
     * @return 产品价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置产品价格
     *
     * @param price 产品价格
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
