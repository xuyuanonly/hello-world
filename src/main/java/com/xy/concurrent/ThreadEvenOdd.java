package com.xy.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Hugo.xu on 2018/5/3.
 * Description:
 * 注意点：1、使用condition需要上锁
 *         2、每次打印前都需要判断（在for里面判断），用while
 */
public class ThreadEvenOdd {

    static volatile Boolean shouldOdd = true;
    static Lock lock = new ReentrantLock();
    static volatile  Condition oddRun = lock.newCondition();
    static volatile  Condition evenRun = lock.newCondition();
    public static void main(String[] args)  {

        Thread oddThread = new Thread(){
            @Override
            public void run() {
                lock.lock();
                for (int i = 1; i < 100; i = i+2) {
                    while (!shouldOdd){
                        try {
                            oddRun.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i+" ");
                    shouldOdd = false;
                    evenRun.signal();
                }
                lock.unlock();
            }
        };
        Thread evenThread = new Thread(){
            @Override
            public void run() {
                lock.lock();
                for (int i = 2; i <= 100; i=i+2) {
                    while (shouldOdd){
                        try {
                            evenRun.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i+" ");
                    shouldOdd = true;
                    oddRun.signal();
                }
                lock.unlock();
            }
        };
        oddThread.start();
        evenThread.start();
    }
}
