package com.juc.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/22 16:34
 * @description ：volatile 关键字
 */
public class VolatileTest {

    /**
     * 定义一个共享变量，需要被volatile修饰，否则线程不能及时感知
     */
    static   Boolean flag=false;

    public static void main(String[] args) {

        List<Integer> numList=new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        TestManager.initList(numList,characterList);

        new Thread(()->{
            for (Integer num:numList){
                //flag为false才执行输出打印，否则自旋
                while (flag){}
                System.out.println(num);
                //打印完修改变量值
                flag=true;
            }
        },"t1").start();

        new Thread(()->{

            for(Character character:characterList){
                //flag为true才执行输出打印，否则自旋
                while (!flag){}
                System.out.println(character);
                //打印完修改变量值
                flag=false;
            }
        },"t2").start();

    }
}
