//322. Coin Change
//https://leetcode.com/problems/coin-change/description/

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 初始化为不可能达到的值，amount + 1 足够大
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}


class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        // for (int i = 0; i <= amount; i++) {
        //     dp[i] = Integer.MAX_VALUE;
        // }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // if (dp[amount] == Integer.MAX_VALUE) {
        //     return -1;
        // }
        // return dp[amount];
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
