//494. Target Sum
//https://leetcode.com/problems/target-sum/description/

// 使用DFS的方法：
// 理解问题：
// 问题可以转化为，从数组 nums 中选择一些数字，将这些数字分为两部分，一部分前面加 +，另一部分前面加 -。
// 加 + 的数字总和记为 P，加 - 的数字总和记为 N，则原数组 nums 的总和 S = P + N。
// 题目要求的 target = P - N，即 P = (S + target) / 2。
// 因此，问题转化为从数组 nums 中选择一些数字，使得这些数字的总和为 P = (S + target) / 2。
// 设计DFS函数：
// 设计一个递归函数 dfs(index, sum)，其中 index 表示当前考虑的数字索引，sum 表示到目前为止选择的数字的总和。
// 基本情况：如果 sum 等于 P，则找到一个有效的组合；如果 sum 大于 P 或者 index 超出数组范围，则返回。
// 递归情况：选择当前数字加到 sum 上，然后递归调用 dfs(index + 1, sum + nums[index])；不选择当前数字，直接递归调用 dfs(index + 1, sum)。
// 实现DFS：
// 初始化 dfs 函数，从 index = 0, sum = 0 开始。
// 在 dfs 函数中实现上述递归逻辑。
// 优化：
// 使用记忆化搜索来避免重复计算相同 index 和 sum 的情况。
// 使用动态规划的方法：
// 定义状态：
// dp[i][j] 表示前 i 个数字组成和为 j 的方案数。
// 状态转移方程：
// 如果选择第 i 个数字，则 dp[i][j] = dp[i-1][j-nums[i]]；
// 如果不选择第 i 个数字，则 dp[i][j] = dp[i-1][j]；
// 因此，dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j]。
// 初始化：
// dp[0][0] = 1，表示不选择任何数字时，和为 0 的方案数为 1。
// 实现DP：
// 初始化一个二维数组 dp，根据状态转移方程填充数组。
// 返回结果：
// dp[n][P] 将是最终的答案，其中 n 是数组 nums 的长度。


//DFS
class Solution {
    /*
     * Total ways to form the given target, when all possibilities
     * of elements from index -> nums-1 have been considered
     */
    public int totalWays(int[] nums, int target, int index) {
        /** Base condition : At 0th index */
        if (index == 0) {
            int ways = 0;
            /**
             * Need to consider both of these conditions separately,
             * Since, for nums[index]=0, both of the condition will satisfy
             * Hence it need to be counted twice
             */
            if ((target - nums[index] == 0))
                ways++;
            if (target + nums[index] == 0)
                ways++;

            return ways;
        }

        int addition = totalWays(nums, target + nums[index], index - 1);
        int subtraction = totalWays(nums, target - nums[index], index - 1);

        return addition + subtraction;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        return totalWays(nums, target, n - 1);
    }
}

//DP
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = findSum(nums);
        int tsum = (sum+target)/2;
        if((sum+target)%2!=0  || sum<Math.abs(target)) 
            return 0;
        return dpPartition(nums, nums.length, tsum);
        
    }
    int findSum(int[] nums){
        int sum = 0;
        for(int i=0; i< nums.length; i++){
            sum += nums[i];
        }
        return sum;
    }
    int dpPartition(int[] nums, int N, int sum){
        int[][] dp = new int[N+1][sum+1];

        for(int i=0; i<=N; i++){
            dp[i][0] = 1; 
        }
        for(int i=1; i<=sum; i++){
            dp[0][i] = 0; 
        }

        for(int i=1; i<=N; i++){
            for(int j=0; j<=sum; j++){
                if(nums[i-1]<=j){
                    dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][sum];

    }
}
