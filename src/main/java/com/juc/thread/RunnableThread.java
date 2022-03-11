package com.juc.thread;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/23 下午8:36
 * @description
 */
public class RunnableThread implements Runnable{

    @Override
    public void run() {
        System.out.println("我是子线程");
    }

    public static void main(String[] args) {

        System.out.println("主线程开始...");

        RunnableThread runnableThread=new RunnableThread();
        Thread thread=new Thread(runnableThread);
        thread.start();

        System.out.println("主线程结束...");

    }
}
