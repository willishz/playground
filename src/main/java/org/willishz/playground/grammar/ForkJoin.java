package org.willishz.playground.grammar;

import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * jdk8的分治法
 */
public class ForkJoin extends RecursiveTask<Integer> {

    private final Integer THRESHOLD = 10;

    List<Integer> records;

    public ForkJoin() {
        super();
    }

    public ForkJoin setRecords(List<Integer> records) {
        this.records = records;
        return this;
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
        System.out.println(new ForkJoin().setRecords(list).compute());
    }

    @Override
    protected Integer compute() {
        if (records != null && records.size() <= THRESHOLD) {
            Integer result = 0;
            for (Integer i : records) {
                result += i;
            }
            return result;
        } else {
            ForkJoin forkJoinA = new ForkJoin().setRecords(records.subList(0, records.size() / 2));
            ForkJoin forkJoinB = new ForkJoin().setRecords(records.subList(records.size() / 2, records.size()));
            invokeAll(forkJoinA, forkJoinB);
            return forkJoinA.join() + forkJoinB.join();
        }
    }
}