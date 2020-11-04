package com.java.simpleinstance;

/**
 * @Author yuanxiya
 * @Description 懒汉模式：为解决Lazy1中多线程情况下多次创建对象的问题，加锁
 *                        但是这个还有问题：如下
 * @Date 2020/11/4 22:57
 */
public class Lazy2 {

    private static Lazy2 lazy2;

    public Lazy2() {
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    //双层检测锁模式：DCL 懒汉模式
    public static Lazy2 getInstance() {
        if (lazy2 == null) {
            synchronized (Lazy2.class) {
                if (lazy2 == null) {
                    lazy2 = new Lazy2();//不是原子性的
                    /*
                      它是如下底层实现：
                       1、分配内存空间
                       2、通过构造函数，初始化对象
                       3、将对象指向该空间

                       可以123步骤，也可以132：A线程已占用该空间，但是并未初始化对象，B线程到来时发现并没有对象，空指针

                     */
                }
            }

        }
        return lazy2;
    }


    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                Lazy2.getInstance();
            }).start();
        }
    }

}
