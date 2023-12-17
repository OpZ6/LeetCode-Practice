//487. Max Consecutive Ones II
//Medium
//https://leetcode.com/problems/max-consecutive-ones-ii/description/

//First pass:
//forgot to consider the all '1' cases, and misunderstood at first
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int tmp_sum = 0, count = 0, sum = 0, index =0;
      
        while(index < nums.length){
            if(nums[index] == 0){
                break;
            }
            index++;            
        }
      
        if(index == nums.length){
            return index;
        }
      
        for(int i = 0; i <= nums.length; i++){
            if(i < nums.length && nums[i] == 1){
                count++;
            }else{
                int tmp = tmp_sum + count;
                sum = sum > tmp ? sum : tmp;
                tmp_sum = count;
                count = 0;
            }
        }
      
        return sum + 1;
    }
}


//comment:
//special case:
if (nums == null) {
    throw new IllegalArgumentException("Input array is null");
}
//use math function
maxCount = Math.max(maxCount, prevCount + curCount);

//more easily
 for (int n : nums) {
      if (n == 1) {
          curCount++;
      }


//sliding windows: CANNOT handle the follow-up where the input is an infinite stream
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = 0;
        int zeros = 0;
        int maxCount = 0;

        while (end < nums.length) {
            if (nums[end] == 0) {
                zeros++;
            }
            end++;
            while (zeros > 1) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
            }
            maxCount = Math.max(maxCount, end - start);
        }

        return maxCount;
    }
}

//Simple DP Solution. O(n) Runtime O(n) Space.
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        int max = 1;
        int consecutiveOnes = 0;
        for (int i = 1; i <= n; i++) {
            if (nums[i-1] == 1) {
                dp[i] = dp[i-1] + 1;
                consecutiveOnes++;
            }
            else {
                dp[i] = consecutiveOnes + 1; // Flip here
                consecutiveOnes = 0; // do not flip to see if we can get better results in the future
            }
            max = Math.max(dp[i], max); // Take the best value
        }
        return max;
    }
}
