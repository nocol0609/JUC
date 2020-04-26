package com.juc.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/23 12:39
 * @description ：ReentrantLock 可重入测试
 */
public class ReentrantLockTest implements Runnable{

    Lock lock=new ReentrantLock();

    public  void get(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().getId());
            //同一线程 外层函数获得锁之后 ，内层函数仍然有获取该锁的代码，此时不受影响。可以再次获取锁而不会出现死锁
            set();
        }finally {
            lock.unlock();
        }
    }

    public  void set(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().getId());
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantLockTest test=new ReentrantLockTest();
        new Thread(test,"t1").start();
        new Thread(test,"t2").start();
        new Thread(test,"t3").start();
    }
}
