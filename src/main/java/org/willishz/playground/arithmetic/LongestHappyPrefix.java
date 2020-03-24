package org.willishz.playground.arithmetic;

/**
 * 「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
 * <p>
 * 给你一个字符串 s，请你返回它的 最长快乐前缀。
 * <p>
 * 如果不存在满足题意的前缀，则返回一个空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-prefix
 */
public class LongestHappyPrefix {

    public static void main(String[] args) {
        System.out.println(longestPrefix("leetcodeleet"));
        System.out.println(longestPrefix("level"));
        System.out.println(longestPrefix("ababab"));
        System.out.println(longestPrefix("a"));
        System.out.println(longestPrefix("aaaaa"));
    }

    /**
     * 暴力法 超出时间限制
     *
     * @param s
     * @return
     */
    public static String longestHappyPrefix(String s) {
        String result = "";
        for (int i = 1; i < s.length(); i++) {
            if (s == null || i >= s.length() - 1) {
                return result;
            }
            if (s.substring(0, i).equals(s.substring(s.length() - i, s.length()))) {
                result = s.substring(0, i);
            }
        }
        return result;
    }

    public static String longestPrefix(String s) {
        int[] next = getNext(s);
        int n = next[s.length()];
        return s.substring(0, n);
    }

    private static int[] getNext(String s) {
        int[] next = new int[s.length() + 1];
        int i = 0, j = -1;
        next[0] = -1;
        while (i < s.length()) {
            if (j == -1 || s.charAt(j) == s.charAt(i)) {
                // 已有 [0, ..., j - 1] 与 [i - j, ..., i - 1] 匹配, 同时 s[j] == s[i]
                next[++i] = ++j;
                // 匹配长度增加 1, 查看下一个匹配位置
            } else {
                j = next[j];
                // 不匹配, 说明当前查看的前缀太长, 将 j 跳回到上一个可能的匹配位置
            }
        }
        return next;
    }
}