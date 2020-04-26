package com.juc.fairness;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/23 13:22
 * @description ：非公平锁测试
 */
public class NonfairSyncTest implements Runnable {

    private static int number=0;
    private static final Object object = new Object();

    @Override
    public void run() {
        while (number<=100){
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+">>>>>>>>打印数字"+number++);
                //唤醒所有等待线程
                object.notifyAll();
                //当前线程阻塞
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        NonfairSyncTest test=new NonfairSyncTest();
        new Thread(test,"t1").start();
        new Thread(test,"t2").start();
        new Thread(test,"t3").start();
        new Thread(test,"t4").start();
        new Thread(test,"t5").start();
    }
}
