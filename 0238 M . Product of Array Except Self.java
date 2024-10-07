//238. Product of Array Except Self
//https://leetcode.com/problems/product-of-array-except-self/description/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = 1;
        }

        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            output[i] *= left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= right;
            right *= nums[i];
        }

        return output;        
    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] suffixProduct = new int[nums.length];
        int pro = 1;
        suffixProduct[nums.length - 1] = pro;
        for (int i = nums.length - 2; i >= 0; i--) {
            pro *= nums[i + 1];
            suffixProduct[i] = pro;
        }

        pro = 1;
        for (int i = 1; i < nums.length; i++) {
            pro *= nums[i - 1];
            suffixProduct[i] *= pro;
        }

        return suffixProduct;
    }
}
