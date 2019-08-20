import java.util.*;
/*
 * @lc app=leetcode.cn id=447 lang=java
 *
 * [447] 回旋镖的数量
 *
 * https://leetcode-cn.com/problems/number-of-boomerangs/description/
 *
 * algorithms
 * Easy (50.48%)
 * Total Accepted:    2.4K
 * Total Submissions: 4.6K
 * Testcase Example:  '[[0,0],[1,0],[2,0]]'
 *
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k
 * 之间的距离相等（需要考虑元组的顺序）。
 * 
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 * 
 * 示例:
 * 
 * 输入:
 * [[0,0],[1,0],[2,0]]
 * 
 * 输出:
 * 2
 * 
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 */
class Solution {
    private int distance(int[] i, int[] j) {
        int dx = i[0] - j[0];
        int dy = i[1] - j[1];
        return dx * dx + dy * dy;
    }

    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j == i) continue;
                int dist = distance(points[i], points[j]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }

            for (int count : map.values()) ret += count * (count - 1);
        }
        return ret;
    }
}
