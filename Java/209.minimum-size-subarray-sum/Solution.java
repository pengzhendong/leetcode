/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 *
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/description/
 *
 * algorithms
 * Medium (35.04%)
 * Total Accepted:    4.8K
 * Total Submissions: 13.6K
 * Testcase Example:  '7\n[2,3,1,2,4,3]'
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * 
 * 示例: 
 * 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 
 * 进阶:
 * 
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = -1;
        int sum = 0;
        int ret = nums.length + 1;
        while (left < nums.length) {
            if (sum < s && right + 1 < nums.length) sum += nums[++right];
            else sum -= nums[left++];
            if (sum >= s) ret = Math.min(ret, right - left + 1);
        }
        if (ret == nums.length + 1) return 0;
        return ret;
    }
}
