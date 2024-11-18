//45. Jump Game II
//https://leetcode.com/problems/jump-game-ii/description/

//dp
class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            dp[i] = 100001;
        }
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < len) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[len - 1];
    }
}

//贪心Greedy
class Solution {
    public int jump(int[] nums) {
        int jumps = 0; // 记录跳跃次数
        int currentEnd = 0; // 记录当前跳跃能够到达的最远边界
        int farthest = 0; // 记录下一步能到达的最远位置

        for (int i = 0; i < nums.length - 1; i++) {
            // 更新下一步能到达的最远位置
            farthest = Math.max(farthest, i + nums[i]);

            // 当到达当前跳跃的最远边界时
            if (i == currentEnd) {
                jumps++; // 增加跳跃次数
                currentEnd = farthest; // 更新跳跃的边界
            }
        }

        return jumps;
    }
}
