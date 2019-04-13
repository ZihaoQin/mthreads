package com.qzh.mthreads.ReentrantLock;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName:CyclicBarrierDemo.java
 * @Description:TODO （循环栅栏）一种多线程并发使用工具，CyclicBarrier可以接受一个参数作为barrierAction。所谓barrierAction就是
 *                              当计数器一次计数完成后，系统会执行的动作。
 * @Author:qzh
 * @Date: 2019/1/13 10:05
 * @Version 1.0
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclic;

        public Soldier(CyclicBarrier cyclic, String soldier) {
            this.cyclic = cyclic;
            this.soldier = soldier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵到齐
                cyclic.await();
                doWork();
                //等待所有士兵完成工作
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":完成任务");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag){
                System.out.println("司令：[士兵" + N + "个，任务完成！]");
            }else {
                System.out.println("司令：[士兵" + N + "个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int n = 10;
        Thread[] allSoldier = new Thread[n];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(n,new BarrierRun(flag,n));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("集合队伍！");
        for (int i=0; i<n; ++i) {
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Soldier(cyclic, "士兵 " + i));
            allSoldier[i].start();
        }
    }
}
