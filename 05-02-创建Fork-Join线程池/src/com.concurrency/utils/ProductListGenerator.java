package com.concurrency.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品生成器类，根据指定的数量创建产品
 * Author: 王俊超
 * Date: 2014-11-30
 * Time: 18:55
 * Declaration: All Rights Reserved !!!
 */
public class ProductListGenerator {

    /**
     * 产品生产类
     *
     * @param size 产品数量
     * @return 产品集合
     */
    public List<Product> generate(int size) {
        List<Product> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }

        return ret;
    }
}
