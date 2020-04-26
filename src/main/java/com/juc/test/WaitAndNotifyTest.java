package com.juc.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/22 17:19
 * @description ：wait() && notify()
 */
public class WaitAndNotifyTest {

    public static void main(String[] args){

        List<Integer> numList=new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        TestManager.initList(numList,characterList);

        //生成锁对象，多个线程公用
        final Object object=new Object();

        Thread t1=new Thread(()->{
            //t1线程先启动，先获取到object对象的锁
            synchronized (object){
                for (Integer num:numList){
                    System.out.println(num);
                    try {
                        //先唤醒其他等待锁的线程
                        object.notify();
                        //当前线程调用object对象的wait()方法，释放object对象的锁，进入object的等待池等待唤醒
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //循环后最后一次唤醒，否则程序不能停止，因为两个线程打印完之后都进入了阻塞
                object.notify();
            }
        },"t1");

        Thread t2=new Thread(()->{
            //t1释放object对象的锁后，唤醒t2,t2获取锁进入synchronized代码块
            synchronized (object){
                for (Character str:characterList){
                    System.out.println(str);
                    try {
                        //先唤醒t1，仅仅是唤醒，不释放锁
                        object.notify();
                        //打印完进入object的等待池等待唤醒
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        },"t2");


        //先启动t1
        t1.start();
        try {
            //休眠0.5秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //再启动t2
        t2.start();
    }

}
