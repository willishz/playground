package org.willishz.playground.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 * https://leetcode-cn.com/problems/roman-to-integer/
 */
public class RomanToInt {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    private static Map<Character, Integer> roman = new HashMap() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /**
     * 如果后一个字符数字比前一个字符数字大，就要用后者减去前者，并且这两个字符代表的是一个数字。只针对这个做处理，数字相减之后数组下标要+1，循环判断
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> roman = new HashMap<Character, Integer>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
        char[] c = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < c.length; i++) {
            if (i < c.length - 1 && roman.get(c[i]) < roman.get(c[i + 1])) {
                int p = roman.get(c[i + 1]) - roman.get(c[i]);
                sum += p;
                i++;
            } else {
                sum += roman.get(c[i]);
            }
        }
        return sum;
    }

    /**
     * 提前减去
     *
     * @param s
     * @return
     */
    public static int romanToInt2(String s) {
        int result = 0;
        char[] romanChar = s.toCharArray();
        for (int i = 0; i < romanChar.length; i++) {
            // 要处理IV IX XL XC CD CM
            if ((romanChar[i] == 'I' && romanChar[i + 1] == 'V')
                    || (romanChar[i] == 'I' && romanChar[i + 1] == 'V')) {
                result -= 1;
            } else if ((romanChar[i] == 'X' && romanChar[i + 1] == 'L')
                    || (romanChar[i] == 'X' && romanChar[i + 1] == 'C')) {
                result -= 10;
            } else if ((romanChar[i] == 'C' && romanChar[i + 1] == 'D')
                    || (romanChar[i] == 'C' && romanChar[i + 1] == 'M')) {
                result -= 100;
            } else {
                result += roman.get(romanChar[i]);
            }
        }
        return result;
    }

}
