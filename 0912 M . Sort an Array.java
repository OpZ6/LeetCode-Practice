//912. Sort an Array
//https://leetcode.com/problems/sort-an-array/solutions/492042/7-sorting-algorithms-quick-sort-top-down-bottom-up-merge-sort-heap-sort-etc/

// 7 Sorting Algorithms:

// quick sort
// top-down merge sort
// bottom-up merge sort
// heap sort
// selection sort
// insertion sort
// bubble sort (TLE)

//merge sort
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int pivot = nums.length / 2;
        int[] left = sortArray(Arrays.copyOfRange(nums, 0, pivot));
        int[] right = sortArray(Arrays.copyOfRange(nums, pivot, nums.length));
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int left_pos = 0, right_pos = 0, pos = 0;
        
        while (left_pos < left.length || right_pos < right.length) {
            if (left_pos >= left.length ) {
                res[pos] = right[right_pos];
                right_pos++;
            } else if (right_pos >= right.length || left[left_pos] <= right[right_pos]) {
                res[pos] = left[left_pos];
                left_pos++;
            // } else if (left[left_pos] <= right[right_pos]) {
            //     res[pos] = left[left_pos];
            //     left_pos++;
            } else {
                res[pos] = right[right_pos];
                right_pos++;
            }
            pos++;
        }
        return res;
    }
}

//V2
// private int[] merge(int[] left, int[] right) {
//     int[] res = new int[left.length + right.length];
//     int left_pos = 0, right_pos = 0, pos = 0;
    
//     while (left_pos < left.length && right_pos < right.length) {
//         if (left[left_pos] <= right[right_pos]) {
//             res[pos] = left[left_pos];
//             left_pos++;
//         } else {
//             res[pos] = right[right_pos];
//             right_pos++;
//         }
//         pos++;
//     }
    
//     // 处理剩余的元素
//     while (left_pos < left.length) {
//         res[pos] = left[left_pos];
//         left_pos++;
//         pos++;
//     }
    
//     while (right_pos < right.length) {
//         res[pos] = right[right_pos];
//         right_pos++;
//         pos++;
//     }

//     return res;
// }

//V3
// private int[] merge(int[] left, int[] right) {
//     int[] res = new int[left.length + right.length];
//     int left_pos = 0, right_pos = 0, pos = 0;
    
//     while (left_pos < left.length && right_pos < right.length) {
//         res[pos++] = (left[left_pos] <= right[right_pos]) ? left[left_pos++] : right[right_pos++];
//     }
    
//     // 处理剩余的元素
//     while (left_pos < left.length) {
//         res[pos++] = left[left_pos++];
//     }
    
//     while (right_pos < right.length) {
//         res[pos++] = right[right_pos++];
//     }

//     return res;
// }

