package com.java.simpleinstance;

/**
 * @Author yuanxiya
 * @Description 饿汉模式:浪费内存资源
 * @Date 2020/11/4 22:27
 */
public class Hungry {

    private static Hungry hungry = new Hungry();
    public Hungry(){
    }

    public static Hungry getInstance(){
        return hungry;
    }


}
