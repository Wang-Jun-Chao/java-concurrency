package com.concurrency.core;

import com.concurrency.task.Task;
import com.concurrency.utils.Contact;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
        // 创建一个可遍历的映射对象
        ConcurrentSkipListMap<String, Contact> map;
        map = new ConcurrentSkipListMap<>();

        // 创建长度为25的线程数组
        Thread threads[] = new Thread[25];
        int counter = 0;

        // 在25个不同的线程中执行25个任务
        for (char i = 'A'; i < 'Z'; i++) {
            Task task = new Task(map, String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }

        // 等待任务执行完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 输出映射的大小
        System.out.printf("Main: Size of the map: %d\n", map.size());

        // 保存映射条目的对象
        Map.Entry<String, Contact> element;
        // 保存联系人的对象
        Contact contact;

        element = map.firstEntry();
        contact = element.getValue();
        System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());

        // 输出最后一个映射条目
        element = map.lastEntry();
        contact = element.getValue();
        System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());

        // 输出映射的字集
        System.out.printf("Main: Submap from A1996 to B1002: \n");
        ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
        do {
            element = submap.pollFirstEntry();
            if (element != null) {
                contact = element.getValue();
                System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
            }
        } while (element != null);
    }
}
