// 561. Array Partition
// https://leetcode.com/problems/array-partition/description/

class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int odd = 0, even = 1, sum = 0;
        while(even < nums.length){
            sum += Math.min(nums[odd], nums[even]);
            odd += 2;
            even += 2;
        }
        return sum;
    }
}


// already sort！！！
public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int result = 0;
    for(int itr = 0; itr < nums.length; itr += 2){
        result += nums[itr];
    }
    return result;
}
