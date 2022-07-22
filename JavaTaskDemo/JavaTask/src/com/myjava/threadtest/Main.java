package com.myjava.threadtest;

import com.sun.tracing.dtrace.StabilityLevel;

import java.util.concurrent.FutureTask;

public class Main {

    /**
     * 线程初识
     * @param args
     */
    public static void main(String[] args) {
        // write your code here
//        ThreadRunnableDemoMain();
//        ThreadCallableMain();
        ThreadExecuteOrderTest();
    }

    public static void ThreadRunnableDemoMain() {
        ThreadRunnableDemo t = new ThreadRunnableDemo();
        ThreadRunnableDemo t1 = new ThreadRunnableDemo();
        System.out.println("开始执行线程=================");
        new Thread(t).start();
        System.out.println("线程1执行结果=================");
        new Thread(t1).start();
        System.out.println("线程2执行结果=================");
    }

    public static void ThreadCallableMain() {
        for (int i = 1; i < 10; i++) {
            try {
                FutureTask<Object> t = new FutureTask<Object>(new ThreadCallableDemo(i));
                new Thread(t).start();
                if (t.get() == null) {
                    System.out.println("数据为空");
                } else {
                    System.out.println(t.get());
                }
            } catch (Exception ex) {
                System.out.println("线程异常");
            }
        }
    }

    public static void ThreadExecuteOrderTest() {
        /**
         * 1.run方法的线程依然依托主线程，并不会独立运作，所以优先会打印run方法的内容
         * 2.start在主线程之外，单独启动一个新线程，最后也会执行run方法
         */
        new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.println("run启动");
            }catch (Exception ex){

            }
        }).run(); // run方法为同步执行 会阻塞下方代码执行，一定会等待执行完毕之后，才会往下继续执行

        new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.println("start启动");
            }catch (Exception ex){

            }
        }).start(); // start方法为异步执行
        System.out.println("执行完毕");

    }
}
