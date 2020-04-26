package com.juc.test;

import java.util.List;

/**
 * @author ：liuxp
 * @date ：Created in 2020/4/22 16:50
 * @description ：
 */
public class TestManager {

    /**
     * 初始化测试list
     * @param numList 数字集
     * @param characterList 字母集
     */
    public static void initList(List<Integer> numList, List<Character> characterList) {
        for(int i=1;i<=26;i++){
            numList.add(i);
        }

        for (int i = 0; i < 26; i++) {
            characterList.add((char) (65+i));
        }
    }
}
