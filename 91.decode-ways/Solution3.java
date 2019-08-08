import java.util.Map;

/*
 * @lc app=leetcode.cn id=91 lang=java
 *
 * [91] 解码方法
 *
 * https://leetcode-cn.com/problems/decode-ways/description/
 *
 * algorithms
 * Medium (20.06%)
 * Total Accepted:    6.8K
 * Total Submissions: 33.9K
 * Testcase Example:  '"12"'
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 
 * 示例 1:
 * 
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 
 * 示例 2:
 * 
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
class Solution {
    private boolean isValid(String str) {
        int value = Integer.valueOf(str);
        if (value >= 10 && value <= 26) return true;
        return false;
    }

    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') return 0;

        int[] memo = new int[len + 1];
        memo[0] = 1;
        memo[1] = 1;

        for (int i = 2; i <= len; i++) {
            if (s.charAt(i - 1) != '0') memo[i] += memo[i - 1];
            if (isValid(s.substring(i - 2, i))) memo[i] += memo[i - 2];
            if (memo[i] == 0) return 0;
        }
        return memo[len];
    }
}

