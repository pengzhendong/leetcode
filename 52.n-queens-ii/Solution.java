import java.util.*;
/*
 * @lc app=leetcode.cn id=52 lang=java
 *
 * [52] N皇后 II
 *
 * https://leetcode-cn.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (71.44%)
 * Total Accepted:    4.5K
 * Total Submissions: 6.2K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
class Solution {
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    int ret = 0;

    private void putQueen(int n, int index, List<Integer> row) {
        if (index == n) ret += 1;
        else {
            for (int i = 0; i < n; i++) {
                if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                    row.add(i);
                    col[i] = true;
                    dia1[index + i] = true;
                    dia2[index - i + n - 1] = true;
                    putQueen(n, index + 1, row);
                    dia2[index - i + n - 1] = false;
                    dia1[index + i] = false;
                    col[i] = false;
                    row.remove(row.size() - 1);
                }
            }
        }
    }

    public int totalNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        putQueen(n, 0, new ArrayList<>());
        return ret;
    }
}

