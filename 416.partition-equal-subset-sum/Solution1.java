/*
 * @lc app=leetcode.cn id=416 lang=java
 *
 * [416] 分割等和子集
 *
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (39.15%)
 * Total Accepted:    3.4K
 * Total Submissions: 8.5K
 * Testcase Example:  '[1,5,11,5]'
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 注意:
 * 
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 
 * 示例 1:
 * 
 * 输入: [1, 5, 11, 5]
 * 
 * 输出: true
 * 
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 
 * 示例 2:
 * 
 * 输入: [1, 2, 3, 5]
 * 
 * 输出: false
 * 
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
class Solution {
    private int[] nums;
    private int[][] memo;

    private boolean tryPartition(int index, int sum) {
        if (sum == 0) return true;
        if (sum < 0 || index < 0) return false;

        if (memo[index][sum] == -1) memo[index][sum] = (tryPartition(index - 1, sum) || tryPartition(index - 1, sum - nums[index])) ? 1 : 0;
        return memo[index][sum] == 1;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;

        this.nums = nums;
        sum /= 2;
        memo = new int[len][sum + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return tryPartition(len - 1, sum);
    }
}

