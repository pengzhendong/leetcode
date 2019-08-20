import java.util.*;
/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (60.30%)
 * Total Accepted:    6.4K
 * Total Submissions: 10.5K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
class Solution {
    private List<List<String>> ret = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;

    private List<String> generateBoard(int n, List<Integer> row) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sb.setCharAt(row.get(i), 'Q');
            board.add(sb.toString());
            sb.setCharAt(row.get(i), '.');
        }
        return board;
    }

    private void putQueen(int n, int index, List<Integer> row) {
        if (index == n) ret.add(generateBoard(n, row));
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

    public List<List<String>> solveNQueens(int n) {
        for (int j = 0; j < n; j++) {
            sb.append('.');
        }
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        putQueen(n, 0, new ArrayList<>());
        return ret;
    }
}

