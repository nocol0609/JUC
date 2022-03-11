package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description thenApply
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("一阶段：" + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("二阶段：" + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("三阶段：" + result);
            return result;
        });

    }

}
