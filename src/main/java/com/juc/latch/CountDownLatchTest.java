package com.juc.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/23 15:45
 * @description ：CountDownLatch 测试
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch=new CountDownLatch(5);

        Thread waitThread1=new Thread(()->{
            try {
                System.out.println("waitThread1 等待任务就绪");
                latch.await();
                System.out.println("waitThread1开始执行时计数器的值为>>>>>>>>>"+latch.getCount());
                System.out.println("waitThread1 等待结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread waitThread2=new Thread(()->{
            try {
                System.out.println("waitThread2 等待任务就绪");
                latch.await();
                System.out.println("waitThread2开始执行时计数器的值为>>>>>>>>>"+latch.getCount());
                System.out.println("waitThread2 等待结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        waitThread1.start();
        waitThread2.start();

        //休眠一秒
        Thread.sleep(1000);

        //启动5个任务线程
        for(int i=0;i<5;i++){
            final int taskId=i;
            Thread task=new Thread(()->{
                System.out.println("task["+taskId+"]执行任务");
                //执行完任务，计数器减1
                latch.countDown();
            });
            task.start();
        }

        //执行完5个任务线程后，计数器减为0，所有调用了latch的线程被唤醒
        waitThread1.join();
        waitThread2.join();
        System.out.println("test 结束");
    }

}
