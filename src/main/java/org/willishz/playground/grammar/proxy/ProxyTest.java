package org.willishz.playground.grammar.proxy;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * spring aop代理
 * https://blog.csdn.net/qq_35794278/article/details/88243944
 */
public class ProxyTest {
    public static void main(String[] args) {
        ProxyInterface proxyInterface = new MyProxyImpl();
        MyProxyClass proxyClass = new MyProxyClass();
        proxyClass.setTarget(proxyInterface);
        //传递被代理类的类加载器 接口  代理类  返回被代理类
        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(proxyInterface.getClass().getClassLoader(), proxyInterface.getClass().getInterfaces(), proxyClass);
        proxy.helloWorld("hello");
        System.out.println(proxy.getClass().getName());
        System.out.println(proxyInterface.getClass().getName());
    }
}