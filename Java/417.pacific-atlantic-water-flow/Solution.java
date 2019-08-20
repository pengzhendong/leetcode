import java.util.*;
/*
 * @lc app=leetcode.cn id=417 lang=java
 *
 * [417] 太平洋大西洋水流问题
 *
 * https://leetcode-cn.com/problems/pacific-atlantic-water-flow/description/
 *
 * algorithms
 * Medium (37.77%)
 * Total Accepted:    1K
 * Total Submissions: 2.7K
 * Testcase Example:  '[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]'
 *
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * 
 * 提示：
 * 
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 * 
 * 示例：
 * 
 * 给定下面的 5x5 矩阵:
 * 
 * ⁠ 太平洋  ~   ~   ~   ~   ~ 
 * ⁠      ~  1   2   2   3  (5) *
 * ⁠      ~  3   2   3  (4) (4) *
 * ⁠      ~  2   4  (5)  3   1  *
 * ⁠      ~ (6) (7)  1   4   5  *
 * ⁠      ~ (5)  1   1   2   4  *
 * ⁠         *   *   *   *   * 大西洋
 * 
 * 返回:
 * 
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 */
class Solution {
    private int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m, n;

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private void dfs(int[][] matrix, int x, int y, boolean[][] o) {
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX, newY) && !o[newX][newY] && matrix[newX][newY] >= matrix[x][y]) {
                o[newX][newY] = true;
                dfs(matrix, newX, newY, o);
            }
        }
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> ret = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return ret;
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            pacific[i][0] = true;
            atlantic[i][n - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            pacific[0][i] = true;
            atlantic[m - 1][i] = true;
        }
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, pacific);
            dfs(matrix, i, n - 1, atlantic);
        }
        for (int i = 0; i < n; i++) {
            dfs(matrix, 0, i, pacific);
            dfs(matrix, m - 1, i, atlantic);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) ret.add(new int[] {i, j});
            }
        }
        return ret;
    }
}

