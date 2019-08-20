/*
 * @lc app=leetcode.cn id=392 lang=java
 *
 * [392] 判断子序列
 *
 * https://leetcode-cn.com/problems/is-subsequence/description/
 *
 * algorithms
 * Medium (46.47%)
 * Total Accepted:    4.5K
 * Total Submissions: 9.6K
 * Testcase Example:  '"abc"\n"ahbgdc"'
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 
 * 返回 true.
 * 
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 
 * 返回 false.
 * 
 * 后续挑战 :
 * 
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T
 * 的子序列。在这种情况下，你会怎样改变代码？
 * 
 * 致谢:
 * 
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 */
class Solution {
    private int lowerBound(List<Integer> indexes, int preIndex) {
        int begin = 0;
        int end = indexes.size() - 1;
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            if (indexes.get(mid) < preIndex) begin = mid + 1;
            else end = mid;
        }
        int index = indexes.get(begin);

        if (index < preIndex) return -1;
        return index;
    }

    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (!map.containsKey(ch)) map.put(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        int index = -1;
        for (char ch : s.toCharArray()) {
            if (!map.containsKey(ch)) return false;
            index = lowerBound(map.get(ch), index + 1);
            if (index == -1) return false;
        }
        return true;
    }
}
