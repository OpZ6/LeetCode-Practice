//209. Minimum Size Subarray Sum
//https://leetcode.com/problems/minimum-size-subarray-sum/description/

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = 0, sum = nums[start], len = nums.length + 1;
        while(end < nums.length){
            if(sum < target){
                end++;
                if(end == nums.length){
                    break;
                }
                sum += nums[end];
            }
            if(sum >= target){
                len = Math.min(len, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        if(start == 0 && end == nums.length){
            return 0;
        }        
        return len;
    }
}

//max number use Integer.MAX_VALUE;
//two pointer use while loop with while loop

public int minSubArrayLen(int s, int[] a) {
  if (a == null || a.length == 0)
    return 0;
  
  int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
  
  while (j < a.length) {
    sum += a[j++];
    
    while (sum >= s) {
      min = Math.min(min, j - i);
      sum -= a[i++];
    }
  }
  
  return min == Integer.MAX_VALUE ? 0 : min;
}

// binery search explanation：https://leetcode.wang/leetcode-209-Minimum-Size-Subarray-Sum.html
//不同之处在于这里求总和时候，可以利用 sums 数组，不再需要累加了。
//比如求从第 i 个数字开始，总和大于等于 s 时的长度，我们只需要找从第 i + 1 个数字到第几个数字的和大于等于 s - nums[i] 即可。求 i + 1 到 j 的所有数字的和的话，前边已经说明过了，也就是 sums[j] - sums[i]。
public int minSubArrayLen(int s, int[] nums) {
    int n = nums.length;
    if (n == 0) {
        return 0;
    }
    int[] sums = new int[n];
    sums[0] = nums[0];
    for (int i = 1; i < n; i++) {
        sums[i] = nums[i] + sums[i - 1];
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
        int s2 = s - nums[i];
        //二分查找，目标值是 s2 + sums[i]
        int k = binarySearch(i, n - 1, sums, s2 + sums[i]);
        if (k != -1) {
            min = Math.min(min, k - i + 1);
        }

    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
//寻求刚好大于 target 的 sums 的下标，也就是大于等于 target 所有 sums 中最小的那个
private int binarySearch(int start, int end, int[] sums, int target) {
    int mid = -1;
    while (start < end) {
        mid = (start + end) >>> 1;
        if (sums[mid] >= target) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    //是否找到，没有找到返回 -1
    return sums[start] >= target ? start : -1;
}
