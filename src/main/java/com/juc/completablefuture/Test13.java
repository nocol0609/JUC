package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description anyOf
 */
public class Test13 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(random.nextInt(5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                });

        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(random.nextInt(1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "world";
                });
        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
        System.out.println(result.get());

    }

}
