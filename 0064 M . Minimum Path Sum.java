//64. Minimum Path Sum
//https://leetcode.com/problems/minimum-path-sum/description/

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid.length < 1) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        // int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }

        return grid[row - 1][col - 1];
    }
}

//gpt
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 更新第一行的累积和
        for (int j = 1; j < cols; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        // 更新第一列的累积和
        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // 更新其余位置的累积和
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        // 返回右下角的最小路径和
        return grid[rows - 1][cols - 1];
    }
}
