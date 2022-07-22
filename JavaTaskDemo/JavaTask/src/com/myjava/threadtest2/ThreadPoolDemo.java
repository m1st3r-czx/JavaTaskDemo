package com.myjava.threadtest2;

import java.util.*;
import java.util.concurrent.*;


public class ThreadPoolDemo {
    public void ThreadPoolTest() {
//        Executor executor = new ThreadPoolExecutor(20, 20, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
//        for (int i = 0; i < 50; i++) {
//            executor.execute(new ThreadTaskDemo());
//        }
//        try {
//            executor.wait();
//            System.out.println("执行完成");
//        } catch (Exception ex) {
//
//        }

        Executor executor = new ThreadPoolExecutor(5, 50, 3L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(40));
        for (int i = 0; i < 70; i++) {
            executor.execute(new ThreadTaskDemo());
        }

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
//        for (int i = 0; i < 50; i++) {
//            threadPoolExecutor.execute(new ThreadTaskDemo());
//        }
//        System.out.println("线程池内的线程数量：" + threadPoolExecutor.getPoolSize());
//        System.out.println("线程池内要执行的线程数量：" + threadPoolExecutor.getTaskCount());
//        while (true){
//            if (threadPoolExecutor.getTaskCount() == threadPoolExecutor.getCompletedTaskCount()) {
//                System.out.println("执行完成");
//                break;
//            }
//        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 100, 3L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(50));
        for (int i = 0; i < 151; i++) {
            threadPoolExecutor.execute(new ThreadTaskDemo());
        }
        System.out.println("线程池内的线程数量：" + threadPoolExecutor.getPoolSize());
        System.out.println("线程池内要执行的线程数量：" + threadPoolExecutor.getTaskCount());
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            System.out.println("已经执行的数量：" + threadPoolExecutor.getCompletedTaskCount());
            if (threadPoolExecutor.getTaskCount() == threadPoolExecutor.getCompletedTaskCount()) {
                System.out.println("执行完成");
                threadPoolExecutor.shutdown(); // 销毁线程池
                break;
            }
        }
    }

    public void ThreadPoolTest1() {
        // 多线程list的写入操作
        Vector<Integer> list1 = new Vector<>();
        List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list3 = new ArrayList<>();
        Executor executor = new ThreadPoolExecutor(5, 50, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(40));
        for (int i = 1; i <= 70; i++) {
            int finalI = i;
            Runnable runnable = () -> {
//                list.add(finalI);
                list1.add(finalI);
                list2.add(finalI);
                list3.add(finalI);
                System.out.println("这是第：" + finalI + "个，正在执行：" + Thread.currentThread().getName() + "耗时：" + new Date().getTime());
            };
            executor.execute(runnable);
        }
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println(list1);
    }

    public void ThreadPoolTest2() {
        // 不同写法测试
        Executor executor = new ThreadPoolExecutor(10, 20, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(30));
        int count = 50;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                System.out.println("正在执行：" + Thread.currentThread().getName() + "耗时：" + new Date().getTime());
                countDownLatch.countDown();
            };
            executor.execute(runnable);
        }
        try {
            countDownLatch.await();
        } catch (Exception ex) {

        }

        System.out.println("执行完毕");

        Executor executor1 = new ThreadPoolExecutor(10, 20, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(30));
        int count1 = 50;
        CountDownLatch countDownLatch1 = new CountDownLatch(count);
        for (int i = 1; i <= count1; i++) {
            Runnable runnable = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                System.out.println("正在执行：" + Thread.currentThread().getName() + "耗时：" + new Date().getTime());
                countDownLatch.countDown();
            });
            executor1.execute(runnable);
        }
        try {
            countDownLatch1.await();
        } catch (Exception ex) {

        }

        System.out.println("执行完毕");

    }
}
