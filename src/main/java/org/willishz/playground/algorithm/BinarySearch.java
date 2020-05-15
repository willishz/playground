package org.willishz.playground.arithmetic;

/**
 * 二分法查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12, 13, 53, 365, 23425, 124564564};
        System.out.println(nums[binarySearch(nums, 124564564)]);
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2; // notice
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1; // notice: + 1
            } else if (nums[mid] > target) {
                right = mid - 1; // notice: - 1
            }
        }
        return -1;
    }

}