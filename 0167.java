// 167. Two Sum II - Input Array Is Sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        int[] res = new int[2];
        while(start < end){
            int sum = numbers[start] + numbers[end];
            if(sum > target){
                end--;
            }
            if(sum < target){
                start++;
            }
            if(sum == target){
                res[0] = start + 1;
                res[1] = end + 1;
                return res;
            }
        }
        return null;
    }
}

//return new int[] {l+1, r+1};
public int[] twoSum(int[] nums, int target) {
	int l = 0, r = nums.length - 1;
	
	while (nums[l] + nums[r] != target) {
		if (nums[l] + nums[r] < target) l++;
		else r--;
	}

	return new int[] {l+1, r+1};
}
