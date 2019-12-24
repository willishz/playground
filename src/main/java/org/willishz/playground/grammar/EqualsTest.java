package org.willishz.playground.grammar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * 内存地址判断
 * https://blog.csdn.net/u013366617/article/details/83618361
 */
public class EqualsTest {

    @Rule
    public TestName name = new TestName();

    @Test
    public void integerWrapperClass() {
        System.out.println("\n----------- " + name.getMethodName() + " ----------");
        Integer a = 127; // Integer对于-128到127之间的数字在缓存中拿，不是创建新对象
        Integer b = 128;
        Integer aa = 127;
        Integer bb = 128;
        Integer aaa = new Integer(127);
        Integer bbb = new Integer(128);
        System.out.println("a == aa " + (a == aa)); // true
        System.out.println("b == bb " + (b == bb)); // false
        System.out.println("a == aaa " + (a == aaa)); // false
        System.out.println("b == bbb " + (b == bbb)); // false
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(aa));
        System.out.println(System.identityHashCode(bb));
        System.out.println(System.identityHashCode(aaa));
        System.out.println(System.identityHashCode(bbb));
    }

    /**
     * String的hashCode不返回内存地址,只能用System.identityHashCode
     */
    @Test
    public void identityHashCode() {
        System.out.println("\n---------- " + name.getMethodName() + " ----------");
        String a = new String("sss");
        String b = new String("sss");
        String c = "sss";
        String d = "s" + "s" + "s";
        String e = new String("s") + "s" + "s"; // 常量字符串和变量拼接时,会调用stringBuilder.append()在堆上创建新的对象
        String ee = e.intern();
        String f = "s" + new String("s") + new String("s"); // 常量字符串和变量拼接时,会调用stringBuilder.append()在堆上创建新的对象
        String ff = f.intern();
        String g1 = "s";
        String g2 = "ss";
        String g = g1 + g2; // false，因为s2+s3实际上是使用StringBuilder.append来完成，会生成不同的对象
        final String h1 = "s";
        final String h2 = "ss";
        String h = h1 + h2; // true，因为final变量在编译后会直接替换成对应的值
        System.out.println("a:" + System.identityHashCode(a));
        System.out.println("b:" + System.identityHashCode(b));
        System.out.println("c:" + System.identityHashCode(c));
        System.out.println("d:" + System.identityHashCode(d));
        System.out.println("e:" + System.identityHashCode(e));
        System.out.println("f:" + System.identityHashCode(f));
        System.out.println("ee:" + System.identityHashCode(ee));
        System.out.println("ff:" + System.identityHashCode(ff));
        System.out.println("g:" + System.identityHashCode(g));
        System.out.println("h:" + System.identityHashCode(h));
    }
}