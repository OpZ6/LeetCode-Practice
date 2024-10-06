//560. Subarray Sum Equals K
//https://leetcode.com/problems/subarray-sum-equals-k/description/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        // 使用哈希表来存储前缀和及其出现的次数
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        // 初始化前缀和为0的情况
        prefixSumCount.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (prefixSumCount.containsKey(sum - k)) {
                count += prefixSumCount.get(sum - k);
            }
            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
