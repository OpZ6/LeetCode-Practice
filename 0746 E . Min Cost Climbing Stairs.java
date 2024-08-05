//746. Min Cost Climbing Stairs
//https://leetcode.com/problems/min-cost-climbing-stairs/description/

//迭代dp
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // dp array to store the minimum cost to reach each step
        int[] dp = new int[n + 1]; 
        
        // Base cases
        dp[0] = 0; // No cost to stand before the first step
        dp[1] = 0; // No cost to stand before the second step
        
        // Fill the dp array
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        
        // The answer is the minimum cost to reach the top of the staircase
        return dp[n];
    }
}

//递归
class Solution {
    private int[] memo;

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        memo = new int[n];
        Arrays.fill(memo, -1);
        
        // 计算到达最后一步或者超出一步的最小成本
        return Math.min(minCost(cost, n-1), minCost(cost, n-2));
    }
    
    private int minCost(int[] cost, int i) {
        if (i < 0) {
            return 0;  // 如果索引小于0，没有成本
        }
        if (i < 2) {
            return cost[i];  // 对于第一步和第二步，直接返回其成本
        }
        if (memo[i] != -1) {
            return memo[i];  // 如果这个位置的最小成本已计算过，直接返回
        }
        
        // 计算到达当前步骤的最小成本
        memo[i] = cost[i] + Math.min(minCost(cost, i-1), minCost(cost, i-2));
        return memo[i];
    }
}
