package com.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description runAfterBoth
 */
public class Test12 {

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

        future1.runAfterBoth(future2, () -> System.out.println("两个任务都完成了"));

    }

}
