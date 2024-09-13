//88. Merge Sorted Array
//https://leetcode.com/problems/merge-sorted-array/description/?envType=problem-list-v2&envId=sorting

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos1 = 0, pos2 = 0;
        int[] nums1c = new int[m];
        for (int i = 0; i < m; i++) {
            nums1c[i] = nums1[i];
        }
        for (int i = 0; i < nums1.length; i++) {
            if (pos2 >= n || (pos1 < m && nums1c[pos1] < nums2[pos2])) {
                nums1[i] = nums1c[pos1];
                pos1++;
            }
            // if (pos1 >= m || (pos2 < n && nums1c[pos1] >= nums2[pos2])) { //error
            else {
                nums1[i] = nums2[pos2];
                pos2++;
            }
        }
    }
}


//chatgpt
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Start merging from the back of nums1
        int i = m - 1; // Pointer for nums1
        int j = n - 1; // Pointer for nums2
        int k = m + n - 1; // Pointer for the merged array in nums1
        
        // Compare elements from the end and place the larger one at the back!!!!!!!
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        
        // If there are remaining elements in nums2, copy them
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
