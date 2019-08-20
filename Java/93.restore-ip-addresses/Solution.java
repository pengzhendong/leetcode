import java.util.*;
/*
 * @lc app=leetcode.cn id=93 lang=java
 *
 * [93] 复原IP地址
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (42.95%)
 * Total Accepted:    6.5K
 * Total Submissions: 15.1K
 * Testcase Example:  '"25525511135"'
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 
 * 示例:
 * 
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
class Solution {
    private List<String> ret = new ArrayList<>();

    private boolean isValid(String s) {
		if (s.charAt(0) == '0') return s.equals("0");
		return Integer.valueOf(s) <= 255;
	}

    private void findIpAddresses(String str, String address, int count) {
        if (count == 3 && isValid(str)) ret.add(address + str);
		else {
            for (int i = 1; i < Math.min(4, str.length()); i++) {
                String current = str.substring(0, i);
                if (isValid(current)) findIpAddresses(str.substring(i), address + current + ".", count + 1);
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
		if (s.length() >= 4 && s.length() <= 12) findIpAddresses(s, "", 0);
		return ret;
    }
}

