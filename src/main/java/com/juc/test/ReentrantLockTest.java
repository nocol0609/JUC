package com.juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/22 18:40
 * @description ：ReentrantLock 结合 Condition
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        List<Integer> numList=new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        TestManager.initList(numList,characterList);

        //默认实现非公平锁机制
        Lock lock=new ReentrantLock();

        //condition对象用于线程的阻塞和唤醒功能,类似 object的 wait()和notify()方法
        Condition condition = lock.newCondition();

        Thread t1=new Thread(()->{
            try {
                lock.lock();
                for (Integer num:numList){
                    System.out.println(num);
                    //唤醒等待线程
                    condition.signal();
                    //当前线程阻塞
                    condition.await();
                }
                //最后打印完再次唤醒等待线程，否则程序无法停止
                condition.signal();

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                //必须放到finally代码块，不管是否发生异常都需手动释放锁
                lock.unlock();
            }
        },"t1");


        Thread t2=new Thread(()->{
            try {
                lock.lock();
                for (Character str:characterList){
                    System.out.println(str);
                    //唤醒等待线程
                    condition.signal();
                    //当前线程阻塞
                    condition.await();
                }
                condition.signal();

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
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
