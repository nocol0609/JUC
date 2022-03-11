package com.juc.thread;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/23 下午8:33
 * @description
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("我是子线程");
    }

    public static void main(String[] args) {
        System.out.println("主线程开始...");

        MyThread myThread=new MyThread();
        myThread.start();

        System.out.println("主线程结束...");
    }
}
