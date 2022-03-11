package com.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/24 下午10:13
 * @description runAsync&supplyAsync
 */
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + Thread.currentThread().getName() + "]" + "执行无返回结果的异步任务");
        });


        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "]" + "执行有返回值的异步任务...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "[" + Thread.currentThread().getName() + "]" + "Hello World";
        });

        String s = future.get();
        System.out.println(s);

    }
}
