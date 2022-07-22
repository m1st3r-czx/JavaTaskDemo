package com.myjava.threadtest1;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier;
    private static int target;

    public CyclicBarrierDemo(int targetCount) {
        target = targetCount;
        cyclicBarrier = new CyclicBarrier(targetCount, () -> {
            System.out.println("恭喜抽奖次数达到：" + targetCount + "消耗时间：" + new Date().getTime());
        });

    }

    public void CyclicDemo() {
        System.out.println("目标次数：" + target);
        for (int i = 1; i < target + 1; i++) {
//            final int count = i;
            int count = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始抽奖" + new Date().getTime());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
