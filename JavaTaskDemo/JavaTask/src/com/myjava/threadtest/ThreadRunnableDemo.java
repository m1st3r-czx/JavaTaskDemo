package com.myjava.threadtest;

/**
 * 写个异步demo
 */
public class ThreadRunnableDemo implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程调用。" + i);
        }

    }
}
