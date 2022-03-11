package com.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description acceptEither
 */
public class Test11 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> {
                    int number = 5;
                    System.out.println("第一阶段start：" + number);
                    try {
                        TimeUnit.SECONDS.sleep(number);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第一阶段end：" + number);
                    return number;
                });

        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> {
                    int number = 5;
                    System.out.println("第二阶段start：" + number);
                    try {
                        TimeUnit.SECONDS.sleep(number);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第二阶段end：" + number);
                    return number;
                });

        future1.runAfterEither(future2, () -> System.out.println("已经有一个任务完成了")).get();

    }

}
