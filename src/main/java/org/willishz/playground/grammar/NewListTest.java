package org.willishz.playground.grammar;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create list best practice
 */
public class NewListTest {

    public static void main(String[] args) {
        NewListTest.bad();

    }

    /**
     * Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的修改方法或者说并没有重写这些方法。
     */
    public static void bad() {
        List myList = Arrays.asList("a", "b", "c");
        myList.add("d"); // UnsupportedOperationException
        myList.remove(1); // UnsupportedOperationException
        myList.clear(); // UnsupportedOperationException
    }

    /**
     * 使用 Guava
     * 对于不可变集合，你可以使用ImmutableList类及其of()与copyOf()工厂方法：（参数不能为空）
     */
    public static void guavaImmutable() {
        List<String> list1 = ImmutableList.of("string", "elements");  // from varargs
        List<String> list2 = ImmutableList.copyOf(new String[]{"a", "b", "c"}); // from array
    }

    /**
     * 对于可变集合，你可以使用Lists类及其newArrayList()工厂方法
     */
    public static void guavaMutable() {
        List<String> l1 = Lists.newArrayList(new ArrayList()); // from collection
        List<String> l2 = Lists.newArrayList(new String[]{"a", "b", "c"}); // from array
        List<String> l3 = Lists.newArrayList("or", "string", "elements"); // from varargs
    }
}
