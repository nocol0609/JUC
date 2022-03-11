package com.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/23 下午8:49
 * @description
 */
public class ExecutorThread {

    public static void main(String[] args) throws Exception{

        System.out.println("主线程开始...");

        ExecutorService executorService= new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是实现了Runnable接口的子线程");
            }
        });


        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "我是实现了Callable接口的子线程的返回值";
            }
        });
        System.out.println("获取到实现了Callable接口的线程的返回值:"+future.get());


        System.out.println("主线程结束...");


    }
}
