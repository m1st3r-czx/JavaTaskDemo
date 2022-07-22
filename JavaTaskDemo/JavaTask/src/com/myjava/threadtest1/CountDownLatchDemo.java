package com.myjava.threadtest1;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class CountDownLatchDemo {
    public void countDownDemo(int countDown) {
        System.out.println("要同步执行的线程数量：" + countDown);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 会创建一定数量的线程，执行
        try {
            for (int i = 0; i < countDown; i++) {
                // 增加休眠之后，明显可以看出线程的创建存在较为明显的延时
                // 关闭休眠后，创建时间相差不大，
//                Thread.sleep(1000);
                new Thread(() -> {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int a = 0;
                    for (int j = 1; j <= 100; j++) {
                        a = a + j;
                    }
                    System.out.println(Thread.currentThread().getName() + "已执行，计算结果为：" + a + "耗时:" + new Date().getTime());
                }, String.valueOf(i)).start();
            }


            countDownLatch.countDown();
        } catch (Exception ex) {

        }
    }

    public void runGameDemo(int countDown) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        System.out.println("=============================这是计数器1的线程==================================");
        try {
            for (int i = 0; i < countDown; i++) {
                new Thread(() -> {
                    try {
                        countDownLatch.await();
                        System.out.println("这是线程1的选手：" + Thread.currentThread().getName() + "到达赛道" + new Date().getTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            Thread.sleep(2000);
            System.out.println("开始执行");
        } catch (Exception exception) {

        }
//        countDownLatch.countDown();
        try {
            System.out.println("=============================这是计数器2的线程==================================");
            for (int i = 0; i < countDown; i++) {
                new Thread(() -> {
                    // 屏蔽等待之后，线程会直接打印里面的内容
                    // 解除屏蔽之后，会等到下面countdown-1完成，线程1，2同时释放
//                    try {
//                        countDownLatch.await();
                    System.out.println("这是线程2的选手：" + Thread.currentThread().getName() + "到达赛道" + new Date().getTime());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }).start();
            }
            Thread.sleep(2000);
            System.out.println("开始执行");
        } catch (Exception exception) {

        }
        countDownLatch.countDown();

//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                try {
//                    //准备完毕……运动员都阻塞在这，等待号令
//                    countDownLatch.await();
//                    String parter = "【" + Thread.currentThread().getName() + "】";
//                    System.out.println(parter + "开始执行……");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//
//        Thread.sleep(2000);// 裁判准备发令
//        countDownLatch.countDown();// 发令枪：执行发令
    }
}
