package org.willishz.playground.arithmetic;

import java.util.Arrays;

/**
 * 删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 2, 3, 4, 4, 4, 5, 6, 6, 7, 7, 8, 8, 8, 9, 10, 10};
        int result = RemoveDuplicatesFromSortedArray.removeDuplicates(nums);
        System.out.println(result);
        nums = Arrays.copyOfRange(nums, 0, result);
        Arrays.stream(nums).forEach(System.out::println);
    }

    /**
     * 双指针法
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 指针一步一挪
     *
     * @param nums
     * @return
     */
    public static int removeDuplicatesFromSortedArray(int[] nums) {
        int removeFlag = 0;
        for (int i = 1; i < nums.length; i++) {
            while (nums[i] == nums[i - 1] && i <= nums.length - removeFlag) {
                removeFlag++;
                for (int k = i; k < nums.length - 1; k++) {
                    nums[k] = nums[k + 1];
                }
            }
        }
        return removeFlag;
    }
}