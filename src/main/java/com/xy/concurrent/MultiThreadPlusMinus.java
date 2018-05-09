package com.xy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Hugo.xu on 2018/5/8.
 * Description:题目如下:四个线程1,2,3,4.线程1,2对变量i加一.线程3,4对变量i减去一.四个线程顺序执行,每个线程每次只执行一次.i的初始值为0,打印结果01210121012...
 */
public class MultiThreadPlusMinus {
    static Lock lock = new ReentrantLock();
    static Condition canPlus = lock.newCondition();
    static Condition canMinus = lock.newCondition();
    public static volatile int i = 0;
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        threadPool.execute(new PlusRunnable());
        threadPool.execute(new PlusRunnable());
        threadPool.execute(new MinusRunnable());
        threadPool.execute(new MinusRunnable());
    }

    static class PlusRunnable implements Runnable{

        @Override
        public void run() {
            while (true){
                lock.lock();
                if (i>1){
                    try {
                        canPlus.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("i = "+ ++i +" "+Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canMinus.signal();
                lock.unlock();
            }
        }
    }


    static class MinusRunnable implements Runnable{
        @Override
        public void run() {
            while(true){
                lock.lock();
                if (i<1){
                    try {
                        canMinus.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("i = "+ --i +" "+Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canPlus.signal();
                lock.unlock();
            }
        }
    }
}


