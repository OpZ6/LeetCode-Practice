//198. House Robber
//https://leetcode.com/problems/house-robber/description/

//递归
class Solution {
    private int[] memo;

    public int rob(int[] nums) {
        this.memo = new int[nums.length];
        Arrays.fill(this.memo, -1);
        return this.robFrom(0, nums);
    }

    private int robFrom(int i, int[] nums) {
        if (i >= nums.length) {
            return 0;
        }

        if (this.memo[i] != -1) {
            return this.memo[i];
        }

        int ans = Math.max(
            this.robFrom(i + 1, nums),
            this.robFrom(i + 2, nums) + nums[i]
        );

        this.memo[i] = ans;
        return ans;
    }
}

//迭代dp
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        // dp数组存储到达每个位置能抢到的最大金额
        int[] dp = new int[n];
        
        // 初始化前两个房子的情况
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        // 填充dp数组
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        
        // dp[n-1]存储的是到达最后一个房子时能抢到的最大金额
        return dp[n-1];
    }
}
