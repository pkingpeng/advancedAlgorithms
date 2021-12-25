package com.pqr.dp;

/**
 * @file: knapsackProblem.java
 * @time: 2021/5/8 12:43 PM
 * @Author by Pking
 */
public class knapsackProblem {
    public static void main(String[] args) {
        /**
         *
         * 算法的主要思想，利用动态规划来解决。每次遍历到的第i个物品，根据w[i]和v[i]来确定是否需要将该物品
         * 放入背包中。即对于给定的 n 个物品，设 v[i]、w[i]分别为第 i 个物品的价值和重量，C 为背包的容量。
         * 再令 v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值。则我们有下面的结果:
         *
         * (1) v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是 0
         * (2) 当 w[i]> j 时:v[i][j]=v[i-1][j] // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个 单元格的装入策略
         * (3) 当 j>=w[i]时: v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
         * // 当 准备加入的新增的商品的容量小于等于当前背包的容量,
         * // 装入的方式:
         * v[i-1][j]: 就是上一个单元格的装入的最大值
         * v[i] : 表示当前商品的价值
         * v[i-1][j-w[i]] : 装入 i-1 商品，到剩余空间 j-w[i]的最大值
         * 当 j>=w[i]时: v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]} :
         *
         * **/
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;
        int n = val.length;
        int[][] v = new int[n + 1][m + 1];

        int[][] path = new int[n + 1][m + 1];

        //initialize
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //dp
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                }
            }
        }

        //输出v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }


    }

}
