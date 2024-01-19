// 1. Two Sum
// https://leetcode.com/problems/two-sum/description/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++){
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}

//POOR hashmap by myself
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         HashMap<Integer, Integer> check = new HashMap<>();
//         for (int i = 0; i < nums.length; i++) {
//             check.put(i, nums[i]);
//         }
//         for (Integer key : check.keySet()) {
//             int tmpTarget = target - check.get(key);
//             if (check.containsValue(tmpTarget)) {
//                 for (int i = 0; (i < nums.length && i != key); i++) {
//                     if (nums[i] == tmpTarget) {
//                         return new int[]{key, i};
//                     }
//                 }
//             }
//         }
//         return null;
//     }
// }

//hashmap 2 pass!!!
//loop throught the original list and if same value with different key. The new one will cover the original key, and since it's loop throught the original key, so their key are different and that's the answer.
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        // Build the hash table
        for (int i = 0; i < n; i++) {
            numMap.put(nums[i], i);
        }

        // Find the complement
        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement) && numMap.get(complement) != i) {
                return new int[]{i, numMap.get(complement)};
            }
        }

        return new int[]{}; // No solution found
    }
}

//hashmap 1 pass
//better loop that do not count twice for same pair!!!
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }

        return new int[]{}; // No solution found
    }
}

//sort + two pointer (more space needed, since a new 2-dimensional list with the index)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[][] numsWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numsWithIndex[i][0] = nums[i];
            numsWithIndex[i][1] = i;
        }

        Arrays.sort(numsWithIndex, Comparator.comparingInt(arr -> arr[0]));
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];
            if (sum == target) {
                return new int[] {numsWithIndex[left][1], numsWithIndex[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{}; // No solution found!
    }
}
