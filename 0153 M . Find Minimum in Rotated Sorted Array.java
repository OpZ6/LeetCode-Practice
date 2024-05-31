//153. Find Minimum in Rotated Sorted Array
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

//在旋转排序数组中，最小值是数组中唯一一个比其右侧邻居小的元素。因此，我们可以使用二分查找来找到这个转折点。

class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                // 最小值在mid的右侧
                left = mid + 1;
            } else {
                // 最小值在mid的左侧或就是mid
                right = mid;
            }
        }

        // 当left和right相等时，left指向的就是最小值
        return nums[left];
    }
}
