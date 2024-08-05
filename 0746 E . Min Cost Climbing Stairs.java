//746. Min Cost Climbing Stairs
//https://leetcode.com/problems/min-cost-climbing-stairs/description/

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
