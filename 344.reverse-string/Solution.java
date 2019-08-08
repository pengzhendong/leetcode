/*
 * @lc app=leetcode.cn id=344 lang=java
 *
 * [344] 反转字符串
 *
 * https://leetcode-cn.com/problems/reverse-string/description/
 *
 * algorithms
 * Easy (63.98%)
 * Total Accepted:    28K
 * Total Submissions: 43.8K
 * Testcase Example:  '"hello"'
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。
 * 
 * 示例 1:
 * 
 * 输入: "hello"
 * 输出: "olleh"
 * 
 * 示例 2:
 * 
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: "amanaP :lanac a ,nalp a ,nam A"
 */
class Solution {
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) swap(chars, left++, right--);
        return new String(chars);
    }
}
