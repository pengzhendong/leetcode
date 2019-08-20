import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (40.45%)
 * Total Accepted:    11.5K
 * Total Submissions: 28.2K
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 
 * 示例:
 * 
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4 
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 
 * 说明:
 * 
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 * 
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
class Solution {
    private int lowerBound(int[] nums, int begin, int end, int value) {
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            if (nums[mid] < value) begin = mid + 1;
            else end = mid;
        }
        return begin;
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        int[] memo = new int[len];
        int pos = 0;
        memo[0] = nums[pos];

        for (int i = 1; i < len; i++) {
            if (nums[i] > memo[pos]) memo[++pos] = nums[i];
            else memo[lowerBound(memo, 0, pos, nums[i])] = nums[i];
        }
        return pos + 1;
    }
}

