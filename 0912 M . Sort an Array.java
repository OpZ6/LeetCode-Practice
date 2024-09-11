//912. Sort an Array
//https://leetcode.com/problems/sort-an-array/solutions/492042/7-sorting-algorithms-quick-sort-top-down-bottom-up-merge-sort-heap-sort-etc/

// 7 Sorting Algorithms:

// quick sort
// top-down merge sort
// bottom-up merge sort
// heap sort
// *selection sort
// insertion sort
// *bubble sort

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


//20240910 Practice Again


class Solution {
    public int[] sortArray(int[] nums) {
        // bubbleSort(nums);
        return nums;
    }

    public void insertSort(int[] nums) {
        // 第一轮循环表示我们每次选择的是数组无序部分中的第一个元素 nums[i]
        // [0, i - 1] 有序 [i, nums.length - 1] 无序
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }
    
    public void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) { // 只需到 n-1，因为最后一个元素自然已经有序
            int min_pos = i;
            for (int j = i + 1; j < nums.length; j++) { // 从 i+1 开始，因为已经以 i 为标准
                if (nums[j] < nums[min_pos]) {
                    min_pos = j;
                }
                //min_pos = nums[j] < nums[min_pos] ? j : min_pos;
            }
            // 只有在 min_pos 发生变化时才进行交换，避免不必要的交换
            if (min_pos != i) {
                swap(nums, i, min_pos);
            }
        }
    }

    //Recursion Verison OF selectSort
    // public void selectSort(int[] nums) {
    //     selectSortRecursive(nums, 0); // 从索引 0 开始排序整个数组
    // }

    // private void selectSortRecursive(int[] nums, int start) {
    //     // 递归终止条件：如果开始位置已经是最后一个元素，则数组已经排序完毕
    //     if (start >= nums.length - 1) {
    //         return;
    //     }

    //     // 找到从 start 到末尾的最小值的索引
    //     int min_pos = start;
    //     for (int i = start + 1; i < nums.length; i++) {
    //         if (nums[i] < nums[min_pos]) {
    //             min_pos = i;
    //         }
    //     }

    //     // 将最小元素放到当前 start 位置
    //     if (min_pos != start) {
    //         swap(nums, start, min_pos);
    //     }

    //     // 递归处理剩余的数组部分
    //     selectSortRecursive(nums, start + 1);
    // }

    
    public void bubbleSort(int[] nums) {
        for (int i = nums.length; i > 0; i--) {
            // pay attention! //i = nums.length --> 1 // j = 0 --> i - 1
            // better! // add flag to point swap
            boolean flag = false;
            for (int j = 0; j < i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
