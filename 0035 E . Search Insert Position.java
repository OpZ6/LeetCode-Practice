// 35. Search Insert Position
// https://leetcode.com/problems/search-insert-position/description/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {//最后条件，检查到最后一个
            int mid= left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;//+1
            } else {
                right = mid - 1;//-1
            }
        }

        return left;//返回插入位置
    }
}
