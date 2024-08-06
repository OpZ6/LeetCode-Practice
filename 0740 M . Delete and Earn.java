//740. Delete and Earn
//https://leetcode.com/problems/delete-and-earn/description/


//转换成198. House Robber
class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 找到数组中的最大值
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        // 统计每个数字出现的总和（不用统计字数，直接统计总和（数量*数字）便于后续计算）
        int[] count = new int[maxNum + 1];
        for (int num : nums) {
            count[num] += num;
        }

        // 动态规划数组
        int[] dp = new int[maxNum + 1];
        dp[0] = 0;
        dp[1] = count[1];

        for (int i = 2; i <= maxNum; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + count[i]);
        }

        return dp[maxNum];
    }
}
