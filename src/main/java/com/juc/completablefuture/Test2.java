package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int i = 10 / 1;

            System.out.println("执行结束！");
            return "test";
        }).exceptionally(throwable -> {
            System.out.println("执行失败：" + throwable.getMessage());
            return "异常xxxx";
        });


        CompletableFuture<String> future2 = future.whenComplete((s, throwable) -> {
            if (throwable != null) {
                System.out.println(s + "执行失败！");
            } else {
                System.out.println("received result is " + s);
            }
        });

        System.out.println(">>>>"+future2.join());

    }

}
