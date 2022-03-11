package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description thenCompose
 */
public class Test4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> {
            int number = 10;
            System.out.println("第一阶段：" + number);
            return number;
        }).thenCompose(number -> CompletableFuture.supplyAsync(() -> {
            int number2 = number * 2;
            System.out.println("第二阶段：" + number2);
            return number2;
        }));
    }

}
