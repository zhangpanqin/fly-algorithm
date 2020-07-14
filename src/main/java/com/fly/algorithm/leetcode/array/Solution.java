package com.fly.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author zhangpanqin
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int length = 0;
        if (Objects.isNull(nums) || nums.length == 0) {
            return length;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                length++;
                continue;
            }
            if (nums[i] != nums[0]) {
                length++;
                for (int i1 = i + 1; i1 < nums.length; i1++) {
                    if (nums[i1] == nums[0]) {
                        continue;
                    }
                    if (nums[i] == nums[i1]) {
                        nums[i1] = nums[0];
                    }
                }
                continue;
            }
            int count = 0;
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if (nums[i1] == nums[0]) {
                    continue;
                }
                if (count == 0 && nums[i1] != nums[i]) {
                    nums[i] = nums[i1];
                    count++;
                    length++;
                }
                if (nums[i1] == nums[i]) {
                    nums[i1] = nums[0];
                }
            }
            if (count == 0) {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
        return length;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        int[] nums = {1, 1, 2};
//        int[] nums = {1, 2};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(solution.removeDuplicates(nums));
    }
}