package org.willishz.playground.algorithm;

import java.util.LinkedList;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
        System.out.println(isMatch("mississippi", "mis*is*ip*."));
        System.out.println(isMatch("ab", ".*c"));
        System.out.println(isMatch("abc", ".*d"));
    }

    private static boolean isMatch(String s, String p) {
        boolean result1 = false;
        boolean result2 = false;
        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();
        // ----- pattern divide begin ------
        LinkedList<char[]> pcharDivide = new LinkedList();
        boolean asterisk = false;
        int section = p.length() - 1;
        for (int k = p.length() - 1; k >= 0; k--) {
            if (asterisk) {
                if (section > (k + 1)) {
                    pcharDivide.addFirst(p.substring(k + 2, section + 1).toCharArray());
                }
                pcharDivide.addFirst(new char[]{pchar[k], pchar[k + 1]});
                section = k - 1;
            }
            asterisk = false;
            if (pchar[k] == '*') {
                asterisk = true;
            }
        }
        if (section > 0) {
            pcharDivide.addFirst(p.substring(0, section + 1).toCharArray());
        }
        if (pcharDivide.isEmpty()) {
            pcharDivide.addLast(p.toCharArray());
        }
        // ----- pattern divide end ------
        // ----- apply pattern divide asc -----
        int si = 0;
        while (!pcharDivide.isEmpty()) {
            char[] regSection = pcharDivide.pollFirst();
            if (si >= s.length()) {
                if (regSection.length == 2 && regSection[1] == '*') {
                    break;
                } else {
                    result1 = false;
                    break;
                }
            }
            if (regSection.length == 2 && regSection[1] == '*') {
                do {
                    if (schar[si] == regSection[0] || regSection[0] == '.') {
                        si++;
                    } else {
                        break;
                    }
                } while (si < s.length());
            } else if (regSection.length == 1 && regSection[0] == '.') {
                si++;
            } else {
                for (char reg : regSection) {
                    if (reg == schar[si]) {
                        si++;
                    }
                }
            }
        }
        result1 = si >= s.length();
        // ----- apply pattern divide desc -----
        int sd = s.length() - 1;
        while (!pcharDivide.isEmpty()) {
            char[] regSection = pcharDivide.pollLast();
            if (sd <= 0) {
                if (regSection.length == 2 && regSection[1] == '*') {
                    break;
                } else {
                    result2 = false;
                    break;
                }
            }
            if (regSection.length == 2 && regSection[1] == '*') {
                do {
                    if (schar[sd] == regSection[0] || regSection[0] == '.') {
                        sd++;
                    } else {
                        break;
                    }
                } while (sd < s.length());
            } else if (regSection.length == 1 && regSection[0] == '.') {
                sd--;
            } else {
                for (char reg : regSection) {
                    if (reg == schar[sd]) {
                        sd--;
                    }
                }
            }
        }
        result2 = sd <= 0;
        return result1 && result2;
    }

}
