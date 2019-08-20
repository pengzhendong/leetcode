import java.util.*;
/*
 * @lc app=leetcode.cn id=149 lang=java
 *
 * [149] 直线上最多的点数
 *
 * https://leetcode-cn.com/problems/max-points-on-a-line/description/
 *
 * algorithms
 * Hard (13.45%)
 * Total Accepted:    1.4K
 * Total Submissions: 10.3K
 * Testcase Example:  '[[1,1],[2,2],[3,3]]'
 *
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * 
 * 示例 1:
 * 
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 
 * 示例 2:
 * 
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    private int GCD(int x, int y) {
        if (y == 0) return x;
        else return GCD(y, x % y);
    }

    private String gradient(Point i, Point j) {
        int dx = i.x - j.x;
        int dy = i.y - j.y;
        int gcd = GCD(dx, dy);
        if (gcd != 0) {
            dx /= gcd;
            dy /= gcd;
        }
        return dx + " " + dy;
    }

    public int maxPoints(Point[] points) {
        if (points.length < 2) return points.length;
        int ret = 0;
        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x != points[j].x || points[i].y != points[j].y) {
                    String grad = gradient(points[i], points[j]);
                    map.put(grad, map.getOrDefault(grad, 0) + 1);
                } else duplicate += 1;
            }

            int maxCount = 0;
            for (int count : map.values()) {
                maxCount = Math.max(count, maxCount);
            }
            ret = Math.max(maxCount + duplicate, ret);
        }
        return ret;
    }
}
