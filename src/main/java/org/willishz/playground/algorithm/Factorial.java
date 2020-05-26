package org.willishz.playground.algorithm;

/**
 * java计算阶乘
 * https://www.cnblogs.com/cjm09/p/9410887.html
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(Factorial.doFactorialRecursion(6));
        System.out.println(Factorial.doFactorialLoop(6));
    }

    /**
     * 递归
     * @param a
     * @return
     */
    public static int doFactorialRecursion(int a) {
        if (a < 0) {
            return -1;
        }
        if (a == 0) {
            return 1;
        } else if (a == 1) { // 递归结束条件
            return 1;
        } else {
            return a * doFactorialRecursion(a - 1);
        }
    }

    /**
     * 循环
     * @param a
     * @return
     */
    public static int doFactorialLoop(int a) {
        if (a < 0) {
            return -1;
        }
        int result = 1;
        for (int i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }
}
