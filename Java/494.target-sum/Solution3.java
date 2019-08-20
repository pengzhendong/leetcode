/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 *
 * https://leetcode-cn.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (40.98%)
 * Total Accepted:    3.6K
 * Total Submissions: 8.9K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或
 * -中选择一个符号添加在前面。
 * 
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 
 * 示例 1:
 * 
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * 一共有5种方法让最终目标和为3。
 * 
 * 注意:
 * 
 * 数组的长度不会超过20，并且数组中的值全为正数。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果为32位整数。
 */
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(S) > sum) return 0;

        int len = nums.length;
        int size = sum * 2 + 1;

        int[][] memo = new int[2][size];
        memo[0][sum] = 1;

        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[(i + 1) % 2], 0);
            for (int j = nums[i]; j < size - nums[i]; j++) {
                memo[(i + 1) % 2][j - nums[i]] += memo[i % 2][j];
                memo[(i + 1) % 2][j + nums[i]] += memo[i % 2][j];
            }
        }

        return memo[len % 2][sum + S];
    }
}

