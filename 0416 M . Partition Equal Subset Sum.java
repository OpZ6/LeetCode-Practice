//416. Partition Equal Subset Sum
//https://leetcode.com/problems/partition-equal-subset-sum/description/

class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // 总和为奇数直接返回false
        if ((totalSum & 1) == 1) return false;
        
        int target = totalSum / 2;
        int maxNum = 0;
        for (int num : nums) {
            if (num > maxNum) maxNum = num;
        }
        
        // 最大值超过target无法分割
        if (maxNum > target) return false;
        // 存在元素等于target直接返回true
        if (maxNum == target) return true;
        
        // 创建动态规划数组
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;  // 空集和为0
        
        for (int num : nums) {
            // 逆序更新避免重复使用元素
            for (int j = target; j >= num; j--) {
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
            // 提前终止优化：如果已经找到目标值
            if (dp[target]) return true;
        }
        
        return dp[target];
    }
}
