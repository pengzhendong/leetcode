/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (54.21%)
 * Total Accepted:    10.8K
 * Total Submissions: 19.9K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 示例 1:
 * 
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 
 * 示例 2:
 * 
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 
 * 说明: 
 * 
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
class Solution {
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int helper(int[] nums, int begin, int end, int k) {
        if (begin == end) return nums[begin];
        int pivot = nums[begin++];
        int i = begin;
        int j = end;
        while (i <= j) {
            if (nums[i] > pivot) swap(nums, i, j--);
            else i++;
        }
        if (end - j >= k) return helper(nums, j + 1, end, k);
        else if (end - j == k - 1) return pivot;
        else return helper(nums, begin, j, k - (end - j) - 1);
    }

    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, k);
    }
}
