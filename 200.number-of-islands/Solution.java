/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿的个数
 *
 * https://leetcode-cn.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (41.76%)
 * Total Accepted:    12K
 * Total Submissions: 28.4K
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * 给定一个由 '1'（陆地）和
 * '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * 
 * 示例 1:
 * 
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * 输出: 1
 * 
 * 示例 2:
 * 
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 
 * 输出: 3
 */
class Solution {
    private int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m, n;
    private boolean[][] visited;

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private void dfs(char[][] grid, int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') dfs(grid, newX, newY);
        }
    }

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;
        visited = new boolean[m][n];
        int ret = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    ret += 1;
                    dfs(grid, i, j);
                }
            }
        }
        return ret;
    }
}

