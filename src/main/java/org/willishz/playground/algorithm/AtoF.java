package org.willishz.playground.algorithm;

/**
 * 将字符串转换为浮点数
 * 它会扫描参数 s 字符串，跳过前面的空白字符（例如空格，tab缩进等)，直到遇上数字或正负符号才开始做转换，而再遇到非数字或字符串结束时(‘\0’)才结束转换，并将结果返回。参数 s 字符串可包含正负号、小数点或E(e)来表示指数部分，如123. 456 或123e-2。
 * 【返回值】返回转换后的浮点数；如果字符串 s 不能被转换为 float，那么返回 0.0。
 * 原文链接：https://blog.csdn.net/SmellyKitty/article/details/47976773
 */
public class AtoF {
    public float atof(String s) {
        float left = 0.0f;
        float right = 0.0f;
        boolean positive = true; // 正负号
        boolean ePo = true;
        int start = 0;
        int size = s.length();
        //跳过空格等
        while (start < size && (s.charAt(start) == ' ' || s.charAt(start) == '\t')) {
            start++;
        }
        //判断是否有正负符号
        if (start < size && s.charAt(start) == '-') {
            positive = false;
            start++;
        } else if (start < size && s.charAt(start) == '+') {
            start++;
        }
        //计算小数点左侧
        while (start < size && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
            left = left * 10 + s.charAt(start) - '0';
            start++;
        }
        //计算小数点右侧
        float order = 1.0f;
        if (start < size && s.charAt(start) == '.') {
            start++;
            while (start < size && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
                order = order / 10;
                right = right + (s.charAt(start) - '0') * order;
                start++;
            }
        }
        //判断是否有科学计数 e
        int or = 0;
        if (start < size && s.charAt(start) == 'e') {
            start++;
            //判断科学计数前的正负
            if (start < size && s.charAt(start) == '-') {
                ePo = false;
                start++;
            } else if (start < size && s.charAt(start) == '+') {
                start++;
            }
            while (start < size && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
                or = or * 10 + s.charAt(start) - '0';
                start++;
            }
        }
        //计算科学计数移动的小数点位数
        order = 1.0f;
        while (or > 0 && ePo) {
            order = order * 10;
            or--;
        }
        while (or > 0 && !ePo) {
            order = order / 10;
            or--;
        }
        //得到结果
        float result = (left + right) * order;
        if (!positive) {
            return -result;
        }
        return result;
    }

}
