//55. Jump Game
//https://leetcode.com/problems/jump-game/description/

class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true; // Edge case: if there's only one element, we're already at the end.
        }

        int maxPos = 0;
        for (int i = 0; i < nums.length; i++) {
            // If we've reached a point we can't pass, return false
            if (maxPos < i) {
                return false;
            }
            // Update the furthest we can reach
            maxPos = Math.max(maxPos, i + nums[i]);
            // If we can reach or exceed the last index, return true
            if (maxPos >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}

public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
