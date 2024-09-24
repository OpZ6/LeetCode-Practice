// https://www.nowcoder.com/practice/4d315070d57b40bea7a8586793d656bc?tpId=354&tqId=10595700&ru=/exam/oj&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Foj

// 描述
// 一位农夫在一个m x n的农场里养了一些奶牛。农夫每次只能向下或向右移动一步去挤奶。但是农场中有些地方有障碍物，农夫不能到达那些位置。而有一个位置上有奶牛，农夫必须经过这个位置挤奶。现在农夫想知道，他从农场的左上角出发，有多少种不同的路径可以到达农场的右下角。

// 农场中的障碍物、空位置和奶牛分别用1、0和2表示。

// 示例1
// 输入：
// [[0,2,0],[0,1,0],[0,0,0]]
// 复制
// 返回值：
// 1
// 复制
// 说明：
// 3x3的农场中有一个障碍物和一个奶牛。
// 从左上角到右下角一共有1条路径经过奶牛的位置：向右(经过奶牛) -> 向右 -> 向下 -> 向下
// 示例2
// 输入：
// [[0,1],[1,2]]
// 复制
// 返回值：
// 0
// 复制
// 说明：
// 没有路径可以经过奶牛的位置到达右下角。
// 备注：
// m == cows.length
// n == cows[i].length
// 1 <= m, n <= 50
// cows[i][j] 为 0 、1或2

import java.util.*;

public class Solution {
    // 辅助方法，计算从起点 (startX, startY) 到终点 (endX, endY) 的路径数
    private int countPaths(int[][] grid, int startX, int startY, int endX, int endY) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // 初始化起点
        dp[startX][startY] = 1;

        // 填充 dp 表
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (grid[i][j] == 1) {
                    // 如果是障碍物，不能到达
                    dp[i][j] = 0;
                } else {
                    if (i > startX) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    if (j > startY) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
        }
        return dp[endX][endY];
    }

    public int uniquePathsWithCows(int[][] cows) {
        int m = cows.length;
        int n = cows[0].length;
        int cowX = -1, cowY = -1;

        // 找到奶牛的位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cows[i][j] == 2) {
                    cowX = i;
                    cowY = j;
                    break;
                }
            }
        }

        // 如果没有奶牛，直接返回 0
        if (cowX == -1 || cowY == -1) {
            return 0;
        }

        // 计算从左上角到奶牛位置的路径数
        int pathsToCow = countPaths(cows, 0, 0, cowX, cowY);
        if (pathsToCow == 0) {
            return 0;  // 如果不能到达奶牛位置，直接返回 0
        }

        // 计算从奶牛位置到右下角的路径数
        int pathsFromCow = countPaths(cows, cowX, cowY, m - 1, n - 1);
        if (pathsFromCow == 0) {
            return 0;  // 如果从奶牛位置不能到达右下角，返回 0
        }

        // 最终结果是两部分路径数的乘积
        return pathsToCow * pathsFromCow;
    }
}
