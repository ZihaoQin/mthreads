package com.qzh.mthreads.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName:SemapDemo.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/15 15:00
 * @Version 1.0
 */
public class SemapDemo implements Runnable {
    final Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            //模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
            semaphore.release();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for(int i=0;i<20;i++) {
            executorService.submit(demo);
        }
    }
}
