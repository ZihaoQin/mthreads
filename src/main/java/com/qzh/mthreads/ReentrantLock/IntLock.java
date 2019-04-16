package com.qzh.mthreads.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:intLock.java
 * @Description:TODO （中断响应）
 * @Author:qzh
 * @Date: 2019/4/15 16:07
 * @Version 1.0
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    //控制加载顺序，方便构造死锁
    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

            try {
                if (lock == 1) {
                    lock1.lockInterruptibly();//可以对中断进行响应的锁申请
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    lock2.lockInterruptibly();
                }else {
                    lock2.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getId() + ":线程退出");
            }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();//thread2中断，同时释放锁，thread1获得锁，继续执行
    }
}
