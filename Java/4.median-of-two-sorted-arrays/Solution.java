/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个有序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (31.99%)
 * Total Accepted:    26.3K
 * Total Submissions: 82.4K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * 则中位数是 2.0
 * 
 * 示例 2:
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 */
class Solution {
    private double findKth(int k, int[] nums1, int[] nums2, int begin1, int begin2){
        int m = nums1.length;
        int n = nums2.length;
        if (begin1 >= m) return nums2[begin2 + k - 1];
        if (begin2 >= n) return nums1[begin1 + k - 1];
        if (k == 1) return Math.min(nums1[begin1], nums2[begin2]);

        int i1 = begin1 + k / 2 - 1;
        int i2 = begin2 + k / 2 - 1;
        int val1 = (i1 < m) ? nums1[i1] : Integer.MAX_VALUE;
        int val2 = (i2 < n) ? nums2[i2] : Integer.MAX_VALUE;

        if (val1 < val2) return findKth(k - k / 2, nums1, nums2, i1 + 1, begin2);
        else return findKth(k - k / 2, nums1, nums2, begin1, i2 + 1);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 0) {
            return (findKth((m + n) / 2, nums1, nums2, 0, 0) + findKth((m + n) / 2 + 1, nums1, nums2, 0, 0)) / 2;
        } else {
            return findKth((m + n) / 2 + 1, nums1, nums2, 0, 0);
        }
    }
}
