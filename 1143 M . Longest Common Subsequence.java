//1143. Longest Common Subsequence
//https://leetcode.com/problems/longest-common-subsequence/description/

//top-down
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int k = text2.length();
        // 创建 memo 表，用于记忆化递归结果
        Integer[][] memo = new Integer[m][k];
        return dp(memo, text1, text2, m - 1, k - 1);
    }

    private int dp(Integer[][] memo, String text1, String text2, int i, int j) {
        // 基础情况：如果有一个字符串遍历完了，公共子序列长度为0
        if (i < 0 || j < 0) {
            return 0;
        }

        // 如果已经计算过，直接返回
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // 如果 text1[i] 和 text2[j] 相同，递归计算 dp[i-1][j-1]
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + dp(memo, text1, text2, i - 1, j - 1);
        } else {
            // 如果不相同，考虑删除 text1[i] 或 text2[j]
            int deleteText1 = dp(memo, text1, text2, i - 1, j);
            int deleteText2 = dp(memo, text1, text2, i, j - 1);
            memo[i][j] = Math.max(deleteText1, deleteText2);
        }
        
        return memo[i][j];
    }
}

//bottom-up
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // 创建 dp 表
        int[][] dp = new int[m + 1][n + 1];
        
        // 填充 dp 表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
}
