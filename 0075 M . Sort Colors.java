//75. Sort Colors
//https://leetcode.com/problems/sort-colors/description/

//count sort
class Solution {
    public void sortColors(int[] nums) {
        int[] store = new int[3];

        for (int num : nums) {
            store[num]++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < store[0]) {
                nums[i] = 0;
            } else if (i < store[0] + store[1]) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }
}
