import java.util.*;
/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前K个高频元素
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (53.87%)
 * Total Accepted:    8.8K
 * Total Submissions: 16.2K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 
 * 示例 2:
 * 
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 
 * 说明：
 * 
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));

        for (int key : map.keySet()) {
            if (pq.size() == k && map.get(key) < map.get(pq.peek())) continue;
            if (pq.size() == k) pq.poll();
            pq.offer(key);
        }

        List<Integer> ret = new ArrayList<>();
        while (!pq.isEmpty()) ret.add(pq.poll());
        return ret;
    }
}
