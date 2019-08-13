/*
 * @lc app=leetcode.cn id=435 lang=java
 *
 * [435] 无重叠区间
 *
 * https://leetcode-cn.com/problems/non-overlapping-intervals/description/
 *
 * algorithms
 * Medium (41.08%)
 * Total Accepted:    2.1K
 * Total Submissions: 5.1K
 * Testcase Example:  '[[1,2]]'
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 
 * 注意:
 * 
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 
 * 示例 1:
 * 
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 
 * 输出: 1
 * 
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 
 * 示例 2:
 * 
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 
 * 输出: 2
 * 
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 
 * 示例 3:
 * 
 * 输入: [ [1,2], [2,3] ]
 * 
 * 输出: 0
 * 
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) return 0;

        Arrays.sort(intervals, (o1, o2) -> (o1[0] != o2[0]) ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));
        
        int[] memo = new int[len];
        Arrays.fill(memo, 1);

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[i][0] >= intervals[j][1]) memo[i] = Math.max(memo[i], memo[j] + 1);
            }
        }

        int ret = 0;
        for (int i = 0; i < len; i++) {
            ret = Math.max(ret, memo[i]);
        }

        return len - ret;
    }
}
