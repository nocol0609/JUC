package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description thenCombine
 */
public class Test5 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> {
                    int number1 = 10;
                    System.out.println("第一阶段：" + number1);
                    return number1;
                });

        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> {
                    int number2 = 20;
                    System.out.println("第二阶段：" + number2);
                    return number2;
                });

        CompletableFuture<Integer> result = future1
                .thenCombine(future2, (number1, number2) -> number1 + number2);
        System.out.println("最终结果：" + result.get());
    }

}
