package org.willishz.playground.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.willishz.HelloworldApplication;
import org.willishz.async.AsyncDemo;
import org.willishz.async.AsyncExceptionDemo;
import org.willishz.constant.HelloWorldConstant;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * https://blog.csdn.net/hry2015/article/details/67640534
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloworldApplication.class)
public class HelloworldApplicationTests {

    @Autowired
    private AsyncExceptionDemo asyncExceptionDemo;

    @Autowired
    private AsyncDemo asyncDemo;

    @Autowired
    private HelloWorldConstant helloWorldConstant;

    @Test
    public void contextLoads() throws InterruptedException, ExecutionException {
        System.out.println(helloWorldConstant.getLanguage());

        asyncDemo.asyncInvokeSimplest();
        asyncDemo.asyncInvokeWithParameter("test");
        Future<String> future = asyncDemo.asyncInvokeReturnFuture(100);
        System.out.println(future.get());

        asyncExceptionDemo.asyncInvokeSimplest();
        asyncExceptionDemo.asyncInvokeWithException("test");
        future = asyncExceptionDemo.asyncInvokeReturnFuture(100);
        System.out.println(future.get());
    }
}
