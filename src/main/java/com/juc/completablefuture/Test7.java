package com.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author seqi.liu@tuta.com
 * @date 2022/2/25 下午10:20
 * @description thenAcceptBoth
 */
public class Test7 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> futrue1 = CompletableFuture.supplyAsync(() -> {
            int number1 = new Random().nextInt(3) + 1;
            try {
                TimeUnit.SECONDS.sleep(number1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一阶段：" + number1);
            return number1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number2 = new Random().nextInt(3) + 1;
            try {
                TimeUnit.SECONDS.sleep(number2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二阶段：" + number2);
            return number2;
        });

        futrue1.thenAcceptBoth(future2, (number1, number2) -> System.out.println("最终结果：" + (number1 + number2)));
    }

}
