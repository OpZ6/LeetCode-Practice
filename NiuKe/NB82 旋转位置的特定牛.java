// https://www.nowcoder.com/practice/4872ba1fef224bd382b49a5958d996ab?tpId=354&tqId=10594882&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 农场里有一群牛，每头牛都有一个编号，这些编号按照从大到小的顺序排列在一个 n 的牛棚中。在某一天，农场主人在预先未知的某个下标 k（0 <= k < nums.length）上对牛棚进行了重新排列，使牛棚变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [7,6,5,4,3,2,1] 在下标 3 处经重新排列后可能变为 [4,3,2,1,7,6,5] 。

// 给你 重新排列后 的牛棚 nums 和一个整数 target ，如果牛棚中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
// 示例1
// 输入：
// [4,3,2,1,7,6,5],1
// 复制
// 返回值：
// 3
// 复制
// 示例2
// 输入：
// [4,3,2,1,7,6,5],8
// 复制
// 返回值：
// -1
// 复制
// 备注：
// 1 <= nums.length <= 5000
// 0 <= nums[i] <= 10000
// nums 中的每个值都 独一无二
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转
// 0 <= target <= 10000

import java.util.*;

public class Solution {
    /**
     * Search for the target in a rotated array sorted in descending order.
     * @param nums int[] - the rotated array
     * @param target int - the target value to search for
     * @return int - the index of the target if found; otherwise, -1
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        // Perform binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if mid is the target
            if (nums[mid] == target) {
                return mid;
            }

            // Determine which side is sorted
            if (nums[left] >= nums[mid]) {
                // Left half is sorted in descending order
                if (nums[left] >= target && target > nums[mid]) {
                    // Target is in the left half
                    right = mid - 1;
                } else {
                    // Target is in the right half
                    left = mid + 1;
                }
            } else {
                // Right half is sorted in descending order
                if (nums[mid] > target && target >= nums[right]) {
                    // Target is in the right half
                    left = mid + 1;
                } else {
                    // Target is in the left half
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }
}
