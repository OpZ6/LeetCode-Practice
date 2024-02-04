// 454. 4Sum II
// https://leetcode.com/problems/4sum-ii/description/

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sums12 = new HashMap<>();
        Map<Integer, Integer> sums34 = new HashMap<>();
        int res = 0;

        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int sum = num3 + num4;
                sums34.put(sum, sums34.getOrDefault(sum, 0) + 1);
            }
        }

        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int sum12 = num1 + num2;
                res += sums34.getOrDefault(-sum12, 0);
            }
        }

        return res;
    }
}

//fastest：use int[] for loop
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        Arrays.sort(nums4);
        int min = Math.min(nums1[0] + nums2[0], -(nums3[n - 1] + nums4[n - 1]));
        int max = Math.max(nums1[n - 1] + nums2[n - 1], -(nums3[0] + nums4[0]));
        
        int[] map = new int[max - min + 1];
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                map[nums1[i] + nums2[j]-min]++;
            }
        }
        int total = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
               total += map[- nums3[i] - nums4[j] - min];                
            }
        }
        return total;
    }
}
