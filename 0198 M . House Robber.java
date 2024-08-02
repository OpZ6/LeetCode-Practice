//198. House Robber
//https://leetcode.com/problems/house-robber/description/

//dp
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
