package com.fly.algorithm.leetcode.array;

/**
 * @author 张攀钦
 * @date 2020-06-15-18:07
 * 按奇偶排序数组
 */
public class A_922_Solution_2 {
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] % 2 != 0) {
                while (A[j] % 2 != 0) {
                    j += 2;
                }
                int tem = A[j];
                A[j] = A[i];
                A[i] = tem;
            }
        }
        return A;

    }
}
