//704. Binary Search
//https://leetcode.com/problems/binary-search/

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;        

        while (left <= right) {//IMPORTANT!!
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;//NO NEED TO CHECK MID AGAIN
            } else {
                right = mid - 1;//NO NEED TO CHECK MID AGAIN
            }
        }
        
        return -1;
    }
}
