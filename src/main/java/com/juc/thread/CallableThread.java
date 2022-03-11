package com.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/23 下午8:38
 * @description
 */
public class CallableThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "我是子线程";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("主线程开始...");

        CallableThread callableThread=new CallableThread();

        FutureTask<String> future=new FutureTask<String>(callableThread);


        Thread thread=new Thread(future);
        thread.start();
        System.out.println("子线程返回:"+future.get());

        System.out.println("主线程结束...");

    }

}
