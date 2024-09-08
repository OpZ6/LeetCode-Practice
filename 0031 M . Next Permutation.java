//31. Next Permutation
//https://leetcode.com/problems/next-permutation/description/

//https://leetcode.com/problems/next-permutation/editorial/

public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//personal recode
public class Solution {
    public void nextPermutation(int[] nums) {
        int target = nums.length - 2;
        // 查找最右侧的小数字（eg 3654中的3
        while (target >= 0 && nums[target] > nums[target + 1]) { //>= 考虑重复数字情况，优化大小顺序符合正常逻辑
            target--;
        }
        // 交换最小大于目标数字，然后反转数列让其成为最小字典序的下一排列
        if (target != -1) {
            int i = nums.length - 1;
            while (i > target && nums[i] < nums[target]) { //<= 考虑重复数字情况，优化大小顺序符合正常逻辑
                i--;
            }
            swap(nums, i, target);
        }
        reverse(nums, target + 1);
    }

    private void reverse(int[] nums, int start) {
        int right = nums.length - 1;
        while (start < right) {
            swap(nums, start, right);
            start++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
