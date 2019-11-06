package org.willishz.playground;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.willishz.playground.PlayGroundApplication;
import org.willishz.playground.async.AsyncDemo;
import org.willishz.playground.async.AsyncExceptionDemo;
import org.willishz.playground.constant.PlayGroundConstant;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * https://blog.csdn.net/hry2015/article/details/67640534
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayGroundApplication.class)
public class PlayGroundApplicationTests {

    @Autowired
    private AsyncExceptionDemo asyncExceptionDemo;

    @Autowired
    private AsyncDemo asyncDemo;

    @Autowired
    private PlayGroundConstant playGroundConstant;

    @Test
    public void contextLoads() throws InterruptedException, ExecutionException {
        System.out.println(playGroundConstant.getLanguage());

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
