package org.willishz.playground.algorithm;

/**
 * 给定一个整数数组 nums,找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/hua-jie-suan-fa-53-zui-da-zi-xu-he-by-guanpengchn/
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(MaxSubArray.maxSubArray(nums));
    }

    /**
     * Dynamic Programming
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int sum = nums[0];    // 用sum保存状态
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
