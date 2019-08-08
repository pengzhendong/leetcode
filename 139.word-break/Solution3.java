/*
 * @lc app=leetcode.cn id=139 lang=java
 *
 * [139] 单词拆分
 *
 * https://leetcode-cn.com/problems/word-break/description/
 *
 * algorithms
 * Medium (39.78%)
 * Total Accepted:    8.1K
 * Total Submissions: 20.1K
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 
 * 说明：
 * 
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 
 * 示例 1：
 * 
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 
 * 示例 2：
 * 
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * 
 * 示例 3：
 * 
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] memo = new boolean[len + 1];
        memo[0] = true;

        for (int i = 1; i <= len; i++) {
            String str = s.substring(0, i);

            for(int j = 0; j < wordDict.size(); j++) {
                String word = wordDict.get(j);

                if (i >= word.length()) {
                    memo[i] = str.endsWith(word) && memo[i - word.length()];
                    if (memo[i]) break;
                }
            }
        }
        
        return memo[len];
    }
}

