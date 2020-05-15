package org.willishz.playground.algorithm;

/**
 * 有1亿个数字，其中有2个是重复的，快速找到它，时间和空间要最优
 * 通过计数排序联想到
 * 原理：把数字值直接映射到数组下标（时间最优），这里重复的数字只有两次，为了空间最优，就用bit来表示（只有0和1），1byte=8bit，一个byte可以存储8个数字的计数。
 * 所以建立数组 byte[] bucket=new byte[(最大值-最小值)/8+1];
 * 原文链接：https://blog.csdn.net/m0_37756306/article/details/105553284
 */
public class MassiveDataFindDuplicated {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        int[] arr = new int[10]; // 1亿长度
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (9 - i) * 10;
        }
        arr[9] = arr[8];
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        byte[] bucket = new byte[(max - min) / 8 + 1];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int j = (num - min) / 8;
            int k = (num - min) % 8;
            if (((bucket[j] >> k) & 1) > 0) { // 重复了
                System.out.println("Number of repeats：" + num);
                break;
            } else {
                bucket[j] |= (1 << k);
            }
        }
        System.out.println("millisecond:" + (System.currentTimeMillis() - time));
    }
}
