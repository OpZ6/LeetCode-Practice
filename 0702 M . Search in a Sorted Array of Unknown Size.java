//702. Search in a Sorted Array of Unknown Size
//https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

// /**
//  * // This is ArrayReader's API interface.
//  * // You should not implement it, or speculate about its implementation
//  * interface ArrayReader {
//  *     public int get(int index) {}
//  * }
//  */

class Solution {
    public int search(ArrayReader reader, int target) {
        int start = 0;

        // No 1 : 找到数组的边界
        int end = 1;
        while (reader.get(end) < target) {
            end *= 2;
        }

        // No 2 : simple set the largest
        // int end = Integer.MAX_VALUE;
        // int end = 10000;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int res = reader.get(mid);
            if (res == target) {
                return mid;
            } else if (res < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return -1;
    }
}
