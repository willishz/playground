package org.willishz.playground.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 */
public class ReverseInteger {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 5, 9, 12, 13, 14, 16, 4, 8};
        int target = 24;
        int[] result = twoSum(nums, target);
        System.out.println(result[0] + " " + result[1]);
    }

    /**
     * 一遍哈希
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> nummap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            nummap.put(nums[i], target - nums[i]);
            if (nummap.containsKey(nummap.get(nums[i])) && (nums[i] != nummap.get(nums[i]))) {
                return new int[]{nums[i], nummap.get(nums[i])};
            }
        }
        return null;
    }

    /**
     * 两遍哈希
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> nummap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            nummap.put(nums[i], target - nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nummap.containsKey(nummap.get(nums[i])) && (nums[i] != nummap.get(nums[i]))) {
                return new int[]{nums[i], nummap.get(nums[i])};
            }
        }
        return null;
    }

}