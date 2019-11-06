package org.willishz.playground.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by luzongwei on 2019/1/31 0031.
 */
public class TestNativeOutOfMemoryError {

    public static void main(String[] args) {

        for (long ii = 0; ii < 100; ii++) {
            ExecutorService exec = Executors.newFixedThreadPool(10000);
            try {
                for (long i = 0; i < 10000; i++) {
                    new Thread(new NormalThread(i)).start();
                }
                exec.shutdown();
                while (!exec.isTerminated()) {
                    if (exec.awaitTermination(5, TimeUnit.SECONDS)) {
//                        log.info("UpdateAccountBalanceJob thread pool close successfully");
                    } else {
//                        log.info("Waiting UpdateAccountBalanceJob thread pool close...");
                    }
                }
            } catch (Exception ex) { // should not reach here
                ex.printStackTrace();
                exec.shutdownNow();
            }
        }
    }
}

class NormalThread extends Thread {

    NormalThread(long i) {
        System.out.println(i);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
