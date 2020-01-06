package org.willishz.playground.grammar.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyClass implements InvocationHandler {
    Object o;

    public void setTarget(Object o) {
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在调用方法之前执行的方法");
        method.invoke(o, args);
        System.out.println("在调用方法之后执行的操作");
        return null;
    }
}