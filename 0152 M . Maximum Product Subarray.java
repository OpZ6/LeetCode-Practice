//152. Maximum Product Subarray
//https://leetcode.com/problems/maximum-product-subarray/description/

class Solution {
    public int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];

        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            
            int candidate1 = dpMax[i-1] * nums[i];
            int candidate2 = dpMin[i-1] * nums[i];
            int currentNum = nums[i];
            
            dpMax[i] = Math.max(currentNum, Math.max(candidate1, candidate2));
            dpMin[i] = Math.min(currentNum, Math.min(candidate1, candidate2));

            res = Math.max(res, dpMax[i]);
        }

        return res;
    }
}
