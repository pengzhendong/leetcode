import java.util.*;
/*
 * @lc app=leetcode.cn id=126 lang=java
 *
 * [126] 单词接龙 II
 *
 * https://leetcode-cn.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (22.29%)
 * Total Accepted:    1.1K
 * Total Submissions: 4.9K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 说明:
 * 
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 *  ["hit","hot","lot","log","cog"]
 * ]
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: []
 * 
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
class Solution {
    List<List<String>> ret = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    LinkedList<String> path = new LinkedList<>();

    public void backTrace(String word, String beginWord, List<String> path) {
        path.add(0, word);
        if (word.equals(beginWord)) {
            ret.add(new ArrayList<>(path));
            path.remove(0);
            return;
        } else {
            if (map.get(word) != null) {
                for (String preWord : map.get(word)) {
                    backTrace(preWord, beginWord, path);
                }
            }
            path.remove(0);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return ret;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Map<String, Integer> ladder = new HashMap<>();
        for (String string : wordList) ladder.put(string, Integer.MAX_VALUE);
        ladder.put(beginWord, 0);

        int minStep = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            int step = ladder.get(curWord) + 1;
            if (step > minStep) break;

            for (int i = 0; i < curWord.length(); i++) {
                StringBuilder builder = new StringBuilder(curWord);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String word = builder.toString();
                    if (ladder.containsKey(word) && step <= ladder.get(word)) {
                        if (step < ladder.get(word)) {
                            ladder.put(word, step);
                            queue.add(word);
                        } 
                        if (!map.containsKey(word)) map.put(word, new LinkedList<>());
                        map.get(word).add(curWord);
                        if (word.equals(endWord)) minStep = step;
                    }
                }
            }
        }
        backTrace(endWord, beginWord, path);

        return ret;
    }
}

