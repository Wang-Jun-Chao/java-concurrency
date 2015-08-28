package com.concurrency.utils;

/**
 * 联系人类
 */
public class Contact {

    /**
     * 联系人的名称
     */
    private String name;

    /**
     * 联系人的电话
     */
    private String phone;

    /**
     * 构造函数
     *
     * @param name  联系人的名称
     * @param phone 联系人的电话
     */
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * 获取 联系人的名称
     *
     * @return 联系人的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取联系人的电话
     *
     * @return 联系人的电话
     */
    public String getPhone() {
        return phone;
    }
}
