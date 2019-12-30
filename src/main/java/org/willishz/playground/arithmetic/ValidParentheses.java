package org.willishz.playground.arithmetic;

import java.util.Stack;

/**
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(ValidParentheses.validParentheses("{[(()]}"));
        System.out.println(ValidParentheses.validParentheses("(((((())))))"));
        System.out.println(ValidParentheses.validParentheses("(){}[]"));
    }

    /**
     * 栈
     * @param str
     * @return
     */
    public static boolean validParentheses(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                char check = stack.pop();
                if (check == '(' && c != ')'
                        || check == '{' && c != '}'
                        || check == '[' && c != ']') {
                    return false;
                }
            }
        }
        return true;
    }
}