package org.willishz.playground.grammar.proxy;

public class MyProxyImpl implements ProxyInterface {
    @Override
    public void helloWorld(String str) {
        System.out.println("MyProxyImpl.helloWorld is " +str);
    }
}