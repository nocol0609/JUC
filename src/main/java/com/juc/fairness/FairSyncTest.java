package com.juc.fairness;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/23 13:22
 * @description ：公平锁测试
 */
public class FairSyncTest implements Runnable {

    private static int number=0;
    Lock lock=new ReentrantLock(true);
    Condition condition = lock.newCondition();

    @Override
    public void run() {
        while (number<=100){
            lock.lock();
            System.out.println(Thread.currentThread().getName()+">>>>>>>>打印数字"+number++);
            //唤醒所有等待线程
            condition.signalAll();
            //当前线程阻塞
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairSyncTest test=new FairSyncTest();
        new Thread(test,"t1").start();
        new Thread(test,"t2").start();
        new Thread(test,"t3").start();
        new Thread(test,"t4").start();
        new Thread(test,"t5").start();
    }
}
