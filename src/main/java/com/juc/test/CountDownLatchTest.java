package com.juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/23 15:12
 * @description ：
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> numList=new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        TestManager.initList(numList,characterList);

        //初始化计数器为1,这里的目的仅仅是为了让其中一线程先启动
        final CountDownLatch latch=new CountDownLatch(1);

        //生成锁对象，多个线程公用
        final Object object=new Object();

        Thread t1=new Thread(()->{
            synchronized (object){
                for (Integer num:numList){
                    System.out.println(num);
                    latch.countDown();
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        },"t1");

        Thread t2=new Thread(()->{
            try {
                //若t1先启动，执行latch.countDown(),此时技术器的值为0，t2再执行latch.await()也不会阻塞
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object){
                for (Character str:characterList){
                    System.out.println(str);
                    latch.countDown();
                    //t2StartFlag=true;
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        },"t2");


        t1.start();
       // Thread.sleep(1000);
        t2.start();
    }

}
