package org.willishz.playground.async;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by luzongwei on 2018/8/16 0016.
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Start:" + System.nanoTime());
        ArrayList<Future> result = new ArrayList();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Future future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(new Random().nextInt(1000));
                    return Thread.currentThread().getName();
                }
            });
            result.add(future); // 先执行完的任务的返回结果集不一定在前面
        }
        executorService.shutdown();
        for (Future future : result) {
            System.out.println(future.get());
        }
        System.out.println("End  :" + System.nanoTime());
    }
}
