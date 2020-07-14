package com.fly.algorithm.leetcode.array;

import java.util.Objects;

/**
 * @author 张攀钦
 * @date 2020-06-15-18:07
 * 按奇偶排序数组
 */
public class A_922_Solution_1 {
    public int[] sortArrayByParityII(int[] A) {
        if (Objects.isNull(A)) {
            return A;
        }
        int[] ret = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0) {
                for (int i1 = 0; i1 < A.length; i1++) {
                    int i2 = A[i1];
                    if (i2 == -1) {
                        continue;
                    }
                    if (A[i1] % 2 == 0) {
                        ret[i] = A[i1];
                        A[i1] = -1;
                        break;
                    }
                }
            } else {
                for (int i1 = 0; i1 < A.length; i1++) {
                    int i2 = A[i1];
                    if (i2 == -1) {
                        continue;
                    }
                    if (i2 % 2 != 0) {
                        ret[i] = i2;
                        A[i1] = -1;
                        break;
                    }
                }
            }

        }
        return ret;
    }
}
