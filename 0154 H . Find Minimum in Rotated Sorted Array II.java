//154. Find Minimum in Rotated Sorted Array II
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

//在旋转排序数组中，最小值是数组中唯一一个比其右侧邻居小的元素。因此，我们可以使用二分查找来找到这个转折点，但是在这个问题中需要注意两头相等情况例如 121 33313等
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                right --;// important!!!
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 当left和right相等时，left指向的就是最小值
        return nums[left];
    }
}
