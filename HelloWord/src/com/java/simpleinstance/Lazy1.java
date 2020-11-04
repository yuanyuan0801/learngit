package com.java.simpleinstance;

/**
 * @Author yuanxiya
 * @Description 懒汉模式：如下有问题，多线程并发时不能准确判断是否为null
 * @Date 2020/11/4 22:33
 */
public class Lazy1 {

    private static Lazy1 lazy;

    public Lazy1(){
        System.out.println(Thread.currentThread().getName()+"ok");
    }



    public static Lazy1 getInstance(){
        if(lazy == null){
            lazy = new Lazy1();
        }
        return lazy;
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                Lazy1.getInstance();
            }).start();//lambda极简模式：（参数）=>{}
        }
    }

}
