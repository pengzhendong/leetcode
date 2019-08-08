/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (30.91%)
 * Total Accepted:    2.6K
 * Total Submissions: 8.4K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 * 
 * 示例：
 * 
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 
 * 说明：
 * 
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || t.length() > s.length()) return "";
        int[] map = new int[256];
        for (int i = 0; i < t.length(); i++) map[t.charAt(i)]++;
        int minLen = s.length() + 1;
        int begin = 0;
        int end = 0;

        int left = 0;
        int right = 0;
        int count = t.length();
        while (right < s.length()) {
            while (right < s.length() && count != 0) {
                if (map[s.charAt(right++)]-- > 0) count--;
            }
            while (count == 0) {
                if (right - left <= minLen) {
                    minLen = right - left;
                    begin = left;
                    end = right;
                }
                if (map[s.charAt(left++)]++ >= 0) count++;
            }
        }
        return s.substring(begin, end);
    }
}
