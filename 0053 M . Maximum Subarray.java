//53. Maximum Subarray
//https://leetcode.com/problems/maximum-subarray/description/
//最合适的解法是使用Kadane's 算法，这是一种动态规划方法

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int sum = nums[0];
        int res = nums[0]; // 将 res 初始化为数组的第一个元素

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            res = Math.max(sum, res);
        }

        return res;
    }
}
