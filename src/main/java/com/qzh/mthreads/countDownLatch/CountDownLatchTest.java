package com.qzh.mthreads.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName:CountDownLatchTest.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/23 14:33
 * @Version 1.0
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(){
            public void run(){
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        Thread thread2 = new Thread(){
            public void run(){
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread2.start();


        try {
            Thread.sleep(3000);
            System.out.println("等待两个子线程执行完毕...");
            latch.await();
            System.out.println("两个子线程已执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
