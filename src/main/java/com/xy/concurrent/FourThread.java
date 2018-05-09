package com.xy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FourThread {
    static volatile int i = 0;
    static Lock lock = new ReentrantLock();
    static Condition firstCon = lock.newCondition();
    static Condition secondCon = lock.newCondition();
    static Condition thirdCon = lock.newCondition();
    static Condition fourthCon = lock.newCondition();
    static volatile int flag = 1;
    public static int incAndGet(){
        return ++i;
    }
    public static int decAndGet(){
        return --i;
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        threadPool.execute(new FirstRunnable());
        threadPool.execute(new SecondRunnable());
        threadPool.execute(new ThirdRunnable());
        threadPool.execute(new FourthRunnable());
    }

    static class FirstRunnable implements Runnable{

        @Override
        public void run() {
            while (true){
                lock.lock();
                while (flag != 1){
                    try {
                        firstCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" : "+incAndGet());
                flag = 2;
                secondCon.signal();
                lock.unlock();
            }
        }
    }

    static class SecondRunnable implements Runnable{

        @Override
        public void run() {
            while (true){
                lock.lock();
                while (flag != 2){
                    try {
                        secondCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" : "+incAndGet());
                flag = 3;
                thirdCon.signal();
                lock.unlock();
            }
        }
    }

    static class ThirdRunnable implements Runnable{

        @Override
        public void run() {
            while(true){
                lock.lock();
                while(flag !=3){
                    try {
                        thirdCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" : "+ decAndGet());
                flag = 4;
                fourthCon.signal();
                lock.unlock();
            }

        }
    }

    static class FourthRunnable implements Runnable{

        @Override
        public void run() {
            while (true){
                lock.lock();
                while(flag != 4){
                    try {
                        fourthCon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" : "+decAndGet());
                flag=1;
                firstCon.signal();
                lock.unlock();
            }
        }
    }
}
