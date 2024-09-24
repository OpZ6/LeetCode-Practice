// https://www.nowcoder.com/practice/0980f806727e48f3b0253243416038c0?tpId=354&tqId=10595698&ru=/exam/oj&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Foj

// 描述
// 在一个农场中，农民们在一片田地里放养了一些奶牛。这片田地可以看作是一个m x n的网格，每个位置都有一头奶牛，每头奶牛都有一个体重。现在农民想知道，如果他每天从左上角到右下角去挤奶，每次只能移动到右边或者下面的相邻位置，那么他需要经过的路径上所有奶牛的体重积是多少？


// 示例1
// 输入：
// [[1,3,1],[1,5,1],[4,2,1]]
// 复制
// 返回值：
// 3
// 复制
// 说明：
// 因为路径 1→3→1→1→1 的总体重积最小。
// 示例2
// 输入：
// [[1,2,3],[4,5,6]]
// 复制
// 返回值：
// 36
// 复制
// 说明：
// 因为路径 1→2→3→6 的总体重积最小。
// 备注：
// m == cows.length
// n == cows[i].length
// 1 <= m, n <= 30
// 1 <= cows[i][j] <= 100

import java.util.*;


public class Solution {
    public long minPathProduct(int[][] cows) {
        int m = cows.length;
        int n = cows[0].length;
        
        // 使用long类型防止溢出
        long[][] dp = new long[m][n];
        
        // 初始化dp数组的起点
        dp[0][0] = cows[0][0];
        
        // 初始化第一列，只能从上面往下乘
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] * cows[i][0];
        }
        
        // 初始化第一行，只能从左往右乘
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] * cows[0][j];
        }
        
        // 动态规划计算其他格子的最小路径乘积
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) * cows[i][j];
            }
        }
        
        // 返回右下角的乘积结果
        return dp[m - 1][n - 1];
    }
}
