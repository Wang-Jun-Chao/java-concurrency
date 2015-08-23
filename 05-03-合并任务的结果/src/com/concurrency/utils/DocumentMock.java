package com.concurrency.utils;

import java.util.Random;

/**
 * 文档模拟对象，根据指定的列数和每行单词数生成文档
 */
public class DocumentMock {
    /**
     * 文档中的单词集合
     */
    private String words[] = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"};

    /**
     * 生成文档
     *
     * @param numLines 文档行数
     * @param numWords 每行单词数
     * @param word     文档中要查找的单词
     * @return 文档
     */
    public String[][] generateDocument(int numLines, int numWords, String word) {

        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)) {
                    counter++;
                }
            }
        }
        System.out.printf("DocumentMock: The word appears %d times in the document.\n", counter);
        return document;
    }
}
