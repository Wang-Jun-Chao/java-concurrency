package com.concurrency.task;

import java.util.concurrent.Phaser;

/**
 * 线程阶段类，控制线程阶段的改变
 * Author: 王俊超
 * Date: 2014-11-25
 * Time: 21:22
 * Declaration: All Rights Reserved !!!
 */
public class MyPhaser extends Phaser {
    /**
     * @param phase             实际的阶段
     * @param registeredParties 注册的线程数
     * @return false表明要进一步执行，true表明已经完成
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    /**
     * 从0->1阶段改变时调用这个方法
     *
     * @return 总是返回false，表明还要断续执行
     */
    private boolean studentArrived() {
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
        return false;
    }

    /**
     * 从1->2阶段改变时调用这个方法
     *
     * @return 总是返回false，表明还要断续执行
     */
    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students has finished the first exercise.\n");
        System.out.printf("Phaser: It's turn for the second one.\n");
        return false;
    }

    /**
     * 从2->3阶段改变时调用这个方法
     *
     * @return 总是返回false，表明还要断续执行
     */
    private boolean finishSecondExercise() {
        System.out.printf("Phaser: All the students has finished the second exercise.\n");
        System.out.printf("Phaser: It's turn for the third one.\n");
        return false;
    }

    /**
     * 从3->4阶段改变时调用这个方法
     *
     * @return 总是返回false，表明还要断续执行
     */
    private boolean finishExam() {
        System.out.printf("Phaser: All the students has finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }
}
