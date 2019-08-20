import java.util.*;
/*
 * @lc app=leetcode.cn id=131 lang=java
 *
 * [131] 分割回文串
 *
 * https://leetcode-cn.com/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (61.94%)
 * Total Accepted:    5.4K
 * Total Submissions: 8.7K
 * Testcase Example:  '"aab"'
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 
 * 返回 s 所有可能的分割方案。
 * 
 * 示例:
 * 
 * 输入: "aab"
 * 输出:
 * [
 * ⁠ ["aa","b"],
 * ⁠ ["a","a","b"]
 * ]
 * 
 */
class Solution {
    private List<List<String>> ret = new ArrayList<>();

    private boolean isValid(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) return false;
        }
        return true;
    }

    private void findPalindrome(String str, List<String> palindromes) {
        if (str.length() == 0) ret.add(new ArrayList<>(palindromes));
        else {
            for (int i = 1; i <= str.length(); i++) {
                String current = str.substring(0, i);
                if (isValid(current)) { 
                    palindromes.add(current);
                    findPalindrome(str.substring(i), palindromes);
                    palindromes.remove(palindromes.size() - 1);
                }
            }
        }
    }

    public List<List<String>> partition(String s) {
        findPalindrome(s, new ArrayList<>());
        return ret;
    }
}

