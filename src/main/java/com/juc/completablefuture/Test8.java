package com.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description thenRun
 */
public class Test8 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> {
            int number = 10;
            System.out.println("第一阶段：" + number);
            return number;
        }).thenRun(() -> System.out.println("thenRun()执行..."));
    }

}
