package com.java.simpleinstance;

/**
 * @Author yuanxiya
 * @Description 懒汉模式：为解决可能的空指针问题，在变量前添加volatile(多线程的可见性、有序性)
 * @Date 2020/11/4 23:07
 */
public class Lazy3 {

    private volatile static Lazy3 lazy3;
    public Lazy3(){
        System.out.println(Thread.currentThread().getName()+"ok");
    }

    public static Lazy3 getInstance(){
        if(lazy3==null){
            synchronized (Lazy3.class){
                if (lazy3==null){
                    lazy3 = new Lazy3();
                }
            }
        }
        return lazy3;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                Lazy3.getInstance();
            }).start();//lambda极简模式：（参数）->{return 参数}
        }
    }
}
