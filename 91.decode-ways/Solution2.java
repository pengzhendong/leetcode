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

    private int[] memo;

    private int decodingsNum(String s, int n) {
        if (n <= 0) return 1;

        if (memo[n] == 0) {
            if (s.charAt(n) != '0') memo[n] += decodingsNum(s, n - 1);
            if (isValid(s.substring(n - 1, n + 1))) memo[n] += decodingsNum(s, n - 2);
        }
        return memo[n];
    }

    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') return 0;
        memo = new int[len];

        return decodingsNum(s, len - 1);
    }
}

