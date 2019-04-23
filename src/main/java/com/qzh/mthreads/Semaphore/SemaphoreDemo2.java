package com.qzh.mthreads.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @ClassName:SemaphoreDemo2.java
 * @Description:TODO （假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。）
 * @Author:qzh
 * @Date: 2019/4/23 18:48
 * @Version 1.0
 */
public class SemaphoreDemo2 {
    public static void main(String[] args) {
        int N = 8;
        Semaphore semaphore = new Semaphore(6);
        for (int i=0;i<N;i++) {
            new Worker(i,semaphore).start();
        }

    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run(){
            try {
                semaphore.acquire(2);  //一共有6个许可，每次调用获取2个，6/2=3，即同时只能三个线程执行
                System.out.println("工人" + this.num + "占用一个机器在生产");
                Thread.sleep(1500);
                System.out.println("工人" + this.num + "释放出机器");
                semaphore.release(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
