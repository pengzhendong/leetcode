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
    private int[] nums;
    private Map<String, Integer> memo;

    private int findWays(int index, int target) {
        if (index == -1) {
            if (target == 0) return 1;
            else return 0;
        }

        String key = index + ", " + target;
        if (!memo.containsKey(key)) {
            int left = findWays(index - 1, target - nums[index]);
            int right = findWays(index - 1, target + nums[index]);
            memo.put(key, left + right);
        }
        return memo.get(key);
    }

    public int findTargetSumWays(int[] nums, int S) {
        this.nums = nums;
        memo = new HashMap<>();
        return findWays(nums.length - 1, S);
    }
}

