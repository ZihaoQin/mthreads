package com.qzh.mthreads.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:ReenterLock.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/15 15:33
 * @Version 1.0
 */
//可重入锁需手动指定合适加锁，合适释放锁
public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for(int j=0;j<1000;j++) {
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread thread1 = new Thread(reenterLock);
        Thread thread2 = new Thread(reenterLock);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
