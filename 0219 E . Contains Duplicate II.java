// 219. Contains Duplicate II
// https://leetcode.com/problems/contains-duplicate-ii/description/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i <  nums.length; i++) {
            for (int j = 1; j <= k && (i + j < nums.length); j++) {
                if (nums[i] == nums[i + j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

//sliding window
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            if (window.contains(nums[i])) {
                return true;
            }
            window.add(nums[i]);
        }
        return false;
    }
}
