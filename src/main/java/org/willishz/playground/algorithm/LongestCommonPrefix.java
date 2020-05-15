package org.willishz.playground.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(LongestCommonPrefix.longestCommonPrefix(Arrays.asList(new String[]{"flower", "flight", "flip", "flick", "fly", "flow", "flower", "flight", "flip", "flick", "fly", "flow"})));
    }

    /**
     * 分治法
     * @param str
     * @return
     */
    public static String longestCommonPrefix(List<String> str) {
        if (str.size() > 4) {
            return longestCommonPrefix(Arrays.asList(new String[]{
                    longestCommonPrefix(str.subList(0, str.size() / 2)),
                    longestCommonPrefix(str.subList(str.size() / 2, str.size()))
            }));
        } else {
            int minSize = Integer.MAX_VALUE;
            for (String s : str) {
                minSize = Math.min(minSize, s.length());
            }
            String result = "";
            for (int i = 0; i < minSize; i++) {
                char c = str.get(0).charAt(i);
                for (int j = 0; j < str.size(); j++) {
                    if (c != str.get(j).charAt(i)) {
                        return result;
                    }
                }
                result += c;
            }
            return result;
        }
    }
}
