import java.util.*;
/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (48.30%)
 * Total Accepted:    19.7K
 * Total Submissions: 40.7K
 * Testcase Example:  '"23"'
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 示例:
 * 
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
class Solution {
    private Map<Character, List<String>> map = new HashMap<>();

    private List<String> findCombination(String digits, int index) {
        List<String> letters = map.get(digits.charAt(index));
        if (index == digits.length() - 1) return letters;

        List<String> strs = findCombination(digits, index + 1);
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < letters.size(); i++) {
            for (int j = 0; j < strs.size(); j++) {
                ret.add(letters.get(i) + strs.get(j));
            }
        }
        return ret;
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        map.put('2', Arrays.asList("a","b","c"));
        map.put('3', Arrays.asList("d","e","f"));
        map.put('4', Arrays.asList("g","h","i"));
        map.put('5', Arrays.asList("j","k","l"));
        map.put('6', Arrays.asList("m","n","o"));
        map.put('7', Arrays.asList("p","q","r","s"));
        map.put('8', Arrays.asList("t","u","v"));
        map.put('9', Arrays.asList("w","x","y","z"));
        return findCombination(digits, 0);
    }
}

