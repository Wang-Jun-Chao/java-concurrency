package com.concurrency.task;

import com.concurrency.utils.Contact;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 任务类，将联系人存储在一个可遍历的图中
 */
public class Task implements Runnable {

    /**
     * 存储联系人的可遍历的映射
     */
    private ConcurrentSkipListMap<String, Contact> map;

    /**
     * 任务编号
     */
    private String id;

    /**
     * 构造函数，初始化属性
     *
     * @param map 存储联系人的可遍历的映射
     * @param id  任务编号
     */
    public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
        this.id = id;
        this.map = map;
    }

    /**
     * 核心方法，产生1000个联系人，并且交它们存储在一个可遍历的映射中
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact(id, String.valueOf(i + 1000));
            map.put(id + contact.getPhone(), contact);
        }
    }
}
