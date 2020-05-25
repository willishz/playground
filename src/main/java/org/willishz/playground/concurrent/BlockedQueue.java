package org.willishz.playground.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockedQueue use ReentrantLock and Condition
 */
public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();
    // 阻塞单列最大长度
    int capacity = 0;
    // 当前已经存在下标：入队
    int putIndex = 0;
    // 当前已经存在下标：出队
    int takeIndex = 0;
    // 元素总数
    int elementsSize = 0;
    // 元素数组
    Object[] items;

    // 构造方法
    public BlockedQueue(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
        System.out.println("capacity=" + capacity + ",items.size=" + items.length);
    }

    // 入队
    public void put(T x) {
        lock.lock();
        try {
            // 队列已满
            while (items.length == elementsSize) {
                // 等待队列不满
                notFull.await();
            }
            // 入队操作...
            items[putIndex] = x;
            if (++putIndex == items.length)
                putIndex = 0;
            ++elementsSize;
            // 入队后, 通知可出队
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(x.toString() + "--入队完成");
        }
    }

    // 出队
    public T take() {
        lock.lock();
        T x = null;
        try {
            // 队列已空
            while (items.length == 0) {
                // 等待队列不空
                notEmpty.await();
            }
            // 出队操作...
            x = (T) items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            elementsSize--;
            // 出队后，通知可入队
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return x;
    }

    public T get(int index) {
        return (T) items[index];
    }

    public static void main(String[] args) throws InterruptedException {
        BlockedQueue<String> blockedQueue = new BlockedQueue<>(20);
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    begin.await();
                    System.out.println("线程0准备完毕");
                    for (int i = 0; i < 10; i++) {
                        blockedQueue.put("线程0-" + i);
                    }
                    System.out.println("线程0入队结束:-------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    begin.await();
                    System.out.println("线程1准备完毕");
                    for (int i = 10; i < 20; i++) {
                        blockedQueue.put("线程1-" + i);
                    }
                    System.out.println("线程1入队结束:-------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            }
        });
        thread0.start();
        thread1.start();
        begin.countDown();
        end.await();
        System.out.println("主线程准备完毕!");
        System.out.println("主线程遍历开始!");
        for (int i = 0; i < 20; i++) {
            System.out.println(blockedQueue.get(i));
        }
        System.out.println("Bingo!");
    }
}
