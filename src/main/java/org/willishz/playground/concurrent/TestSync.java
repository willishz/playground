package org.willishz.playground.concurrent;

/**
 * 存在一个多线程谁先获取到问题，这里其实2个synchronized方法里面的Thread.sheep其实要不要是无所谓的，就为混淆增加难度
 * main thread b=的结果不定,m1和m2谁都有可能先拿到锁
 * https://blog.csdn.net/lirenzuo/article/details/78253481
 */
public class TestSync implements Runnable {
    int b = 100;

    synchronized void m1() throws InterruptedException {
        System.out.println("m1");
        b = 1000;
        Thread.sleep(250); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        System.out.println("m2");
        Thread.sleep(500); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync tt = new TestSync();
        Thread t = new Thread(tt);  //1
        t.start(); //2

        tt.m2(); //3
        System.out.println("main thread b=" + tt.b); //4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
