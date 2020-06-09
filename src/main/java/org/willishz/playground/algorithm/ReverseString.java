package org.willishz.playground.algorithm;

import org.willishz.playground.util.JacksonUtil;

/**
 * 反转字符串,空间复杂度O(1)
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] s = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        ReverseString.reverseString(s);
        System.out.println(JacksonUtil.bean2Json(s));
    }

    /**
     * 双指针
     *
     * @param s
     * @return
     */
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char tmp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = tmp;
        }
    }
}
