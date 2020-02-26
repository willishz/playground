package org.willishz.playground.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JDK8中HashMap中的重要参数
 * 默认容量16
 * 扩容阀值(加载因子) 0.75 扩容后是之前的2倍
 * 树化阀值 8
 * 链表化阀值 6
 *
 * 问题1：为什么不直接采用经过hashCode（）处理的哈希码 作为 存储数组table的下标位置？
 * 结论：容易出现 哈希码 与 数组大小范围不匹配的情况，即 计算出来的哈希码可能 不在数组大小范围内，从而导致无法匹配存储位置
 *
 * 问题2：为什么采用 哈希码 与运算(&) （数组长度-1） 计算数组下标？
 * 结论：根据HashMap的容量大小（数组长度），按需取 哈希码一定数量的低位 作为存储的数组下标位置，从而 解决 “哈希码与数组大小范围不匹配” 的问题
 *
 * 问题3：为什么在计算数组下标前，需对哈希码进行二次处理：扰动处理？
 * 结论：加大哈希码低位的随机性，使得分布更均匀，从而提高对应数组存储下标位置的随机性 & 均匀性，最终减少Hash冲突
 *
 * 问题3：为什么在计算数组下标前，需对哈希码进行二次处理：扰动处理？
 * 结论：加大哈希码低位的随机性，使得分布更均匀，从而提高对应数组存储下标位置的随机性 & 均匀性，最终减少Hash冲突
 * https://blog.csdn.net/carson_ho/article/details/79373134
 */
public class Hash {
    public static void main(String[] args) {
        List<String> citys = new ArrayList() {{
            add("Beijing");
            add("New York");
            add("Tokyo");
            add("Paris");
            add("London");
            add("Hong Kong");
            add("Los Angeles");
            add("Madrid");
            add("Rome");
            add("Singapore");
        }};
        int h = 0;
        for (String city : citys) {
            h = city.hashCode();
            h ^= h >>> 16;
            System.out.println(String.format("hashCode:%d hashCode数组大小内:%d hashCode扰动:%d hashCode扰动数组大小内:%d", city.hashCode(), (city.hashCode() & (citys.size() - 1)), h, h & (citys.size() - 1)));
        }
    }
}
