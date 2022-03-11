package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description thenAccept
 */
public class Test6 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> {
            int number = 10;
            System.out.println("第一阶段：" + number);
            return number;
        }).thenAccept(number -> {
            int result = number * 5;
            System.out.println("第二阶段：" + result);
        });
    }

}
