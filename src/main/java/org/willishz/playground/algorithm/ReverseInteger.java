package org.willishz.playground.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(-1234567890));
    }

    /**
     * 不需要list辅助
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) { // 2147483647
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) { // -2147483648
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * 需要list辅助
     *
     * @param x
     * @return
     */
    public static int reverse2(int x) {
        boolean isNegative = false;
        if (x < 0) {
            x = -x;
        }
        List<Integer> list = new ArrayList();
        int y = x;
        while (y > 0) {
            list.add(y % 10);
            y = y / 10;
        }
        int result = 0;
        for (Integer i : list) {
            if (result > (Integer.MAX_VALUE - i) / 10) {
                return 0;
            }
            result = result * 10 + i;
        }
        if (isNegative) {
            return -result;
        } else {
            return result;
        }
    }

}