//300. Longest Increasing Subsequence
//https://leetcode.com/problems/longest-increasing-subsequence/description/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 初始化，每个元素最少都是一个递增子序列

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 返回 dp 数组中的最大值
        int maxLength = 0;
        for (int len : dp) {
            maxLength = Math.max(maxLength, len);
        }
        return maxLength;
    }
}


//贪心+二分
import java.util.ArrayList;

class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> lis = new ArrayList<>();

        for (int num : nums) {
            int pos = binarySearch(lis, num);
            if (pos == lis.size()) {
                lis.add(num); // 如果没有找到更大的位置，则扩展序列
            } else {
                lis.set(pos, num); // 替换
            }
        }

        return lis.size();
    }

    // 二分查找，寻找第一个大于等于 target 的位置
    private int binarySearch(ArrayList<Integer> lis, int target) {
        int left = 0, right = lis.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (lis.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // 返回插入位置
    }
}
