import java.util.*;
/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (31.50%)
 * Total Accepted:    3.9K
 * Total Submissions: 12.3K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 说明:
 * 
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: 5
 * 
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * ⁠    返回它的长度 5。
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: 0
 * 
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
class Solution {
    private boolean canTransform(String s1, String s2) {
        int num = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) num++;
            if (num > 1) return false;
        }
        return true;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Queue<AbstractMap.SimpleEntry<String, Integer>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new AbstractMap.SimpleEntry<>(beginWord, 1));
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            String curWord = queue.peek().getKey();
            int len = queue.peek().getValue();
            queue.poll();
            if (curWord.equals(endWord)) return len;
            
            for (String word : wordList) {
                if (canTransform(curWord, word) && !visited.contains(word)) {
                    queue.add(new AbstractMap.SimpleEntry<>(word, len + 1));
                    visited.add(word);
                }
            }
        }
        return 0;
    }
}

