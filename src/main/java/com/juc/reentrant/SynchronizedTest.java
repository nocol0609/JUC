package com.juc.reentrant;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/23 12:39
 * @description ：synchronized 可重入测试
 */
public class SynchronizedTest implements Runnable {

    public synchronized void get(){
        System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().getId());
        //同一线程 外层函数获得锁之后 ，内层函数仍然有获取该锁的代码，此时不受影响。可以再次获取锁而不会出现死锁
        set();
    }

    public synchronized void set(){
        System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().getId());
    }


    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SynchronizedTest test=new SynchronizedTest();
        new Thread(test,"t1").start();
        new Thread(test,"t2").start();
        new Thread(test,"t3").start();
    }
}
