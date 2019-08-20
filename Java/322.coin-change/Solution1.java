/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (30.84%)
 * Total Accepted:    9K
 * Total Submissions: 28.9K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 示例 1:
 * 
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 
 * 示例 2:
 * 
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
class Solution {
    private int[] coins;
    private int[][] memo;

    private int bestValue(int index, int amount) {
        if (index < 0 || amount < 0) return -1;
        if (amount == 0) return 0;
        
        if (memo[index][amount] == -2) {
            int value1 = bestValue(index, amount - coins[index]);
            int value2 = bestValue(index - 1, amount);

            if (value1 == -1) memo[index][amount] = value2;
            else {
                if (value2 == -1) memo[index][amount] = value1 + 1;
                else memo[index][amount] = Math.min(value1 + 1, value2);
            }
        }
        return memo[index][amount];
    }

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        int len = coins.length;
        memo = new int[len][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -2);
        }
        return bestValue(len - 1, amount);
    }
}

