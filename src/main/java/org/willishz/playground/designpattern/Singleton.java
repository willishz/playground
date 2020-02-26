package org.willishz.playground.designpattern;

/**
 * 静态内部类实现单例
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * https://www.runoob.com/design-pattern/singleton-pattern.html
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
