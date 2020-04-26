package com.juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/24 12:48
 * @description ：LockSupport  测试
 */
public class LockSupportTest {

    private static Thread t1=null,t2=null;

    public static void main(String[] args) throws InterruptedException {

        List<Integer> numList=new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        TestManager.initList(numList,characterList);

        t1=new Thread(()->{
            for (Integer num:numList){
                System.out.println(num);
                //先唤醒t2
                LockSupport.unpark(t2);
                //t1打印完阻塞
                LockSupport.park();
            }
        },"t1");


        t2=new Thread(()->{
            //LockSupport.park();
            for (Character str:characterList){
                System.out.println(str);
                //唤醒t1
                LockSupport.unpark(t1);

                LockSupport.park();
            }
        },"t2");

        t1.start();
        Thread.sleep(100);
        t2.start();
    }

}
