package com.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description allOf
 */
public class Test15 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("future1完成！");
                    return "future1完成！";
                });

        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("future2完成！");
                    return "future2完成！";
                });

        CompletableFuture<Void> allDoneFuture = CompletableFuture
                .allOf(future1, future2);
        try {
            allDoneFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
