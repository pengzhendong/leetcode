import java.util.*;
/*
 * @lc app=leetcode.cn id=451 lang=java
 *
 * [451] 根据字符出现频率排序
 *
 * https://leetcode-cn.com/problems/sort-characters-by-frequency/description/
 *
 * algorithms
 * Medium (53.51%)
 * Total Accepted:    2.6K
 * Total Submissions: 4.7K
 * Testcase Example:  '"tree"'
 *
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 
 * 示例 1:
 * 
 * 输入:
 * "tree"
 * 
 * 输出:
 * "eert"
 * 
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 
 * 示例 2:
 * 
 * 输入:
 * "cccaaa"
 * 
 * 输出:
 * "cccaaa"
 * 
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 
 * 示例 3:
 * 
 * 输入:
 * "Aabb"
 * 
 * 输出:
 * "bbAa"
 * 
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 */
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i < s.length() + 1; i++) {
            buckets.add(new ArrayList<Character>());
        }
        for (Character ch : map.keySet()) {
            buckets.get(map.get(ch)).add(ch);
        }

        StringBuffer ret = new StringBuffer();
        for (int i = buckets.size() - 1; i >= 0; i--) {
            for (int j = 0; j < buckets.get(i).size(); j++) {
                for (int k = 0; k < i; k++) {
                    ret.append(buckets.get(i).get(j));
                }
            }
        }

        return ret.toString();
    }
}
