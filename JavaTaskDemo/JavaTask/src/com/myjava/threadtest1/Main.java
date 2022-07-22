package com.myjava.threadtest1;

public class Main {
    /**
     * JUC初识
     * @param args
     */
    public static void main(String[] args) {
        // write your code here
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        System.out.println("======================开始执行CountDownLatch==============================================");
//        countDownLatchDemo.countDownDemo(20);

        try {
            countDownLatchDemo.runGameDemo(5);
        }catch (Exception ex){

        }


//        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo(100);
//        System.out.println("======================开始执行CyclicBarrier==============================================");
//        cyclicBarrierDemo.CyclicDemo();

//        cyclicBarrierDemo.CyclicDemo(100);
    }
}
