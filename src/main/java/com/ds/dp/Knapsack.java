package com.ds.dp;

public class Knapsack {
    /**
     *
     * @param C 背包的容量
     * @param v 物品的价格表
     * @param w 物品的重量表
     * @return
     */
    public static int knapsack01(int C, int[] v, int[] w) {
        int dp[][] = new int[v.length + 1][C + 1];
        for (int i = 1; i <= v.length; i++) {
            for (int j = 1; j <= C; j++) {

                if (w[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], v[i - 1] + dp[i - 1][j - w[i - 1]]);
                }

            }
        }
        return dp[v.length][C];

    }

    public static void testKnapsack01() {
        int[] v = {15, 30, 25};
        int[] w = {1, 4, 3};
        System.out.println(knapsack01(4, v, w));
    }

    public static void main(String[] args) {
        testKnapsack01();
    }
}
