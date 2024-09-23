// https://www.nowcoder.com/practice/8bc0369faf7c4ac5ab336f38e859db05?tpId=354&tqId=10595016&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 在农场里，农民们有三个牛群，每个牛群里的牛数量不同。农民们将每个牛群牛的体重按照从小到大的顺序记录在不同的数组中。现在农民们想要知道，如果将这三个牛群合并在一起，那么中位数是多少。请你编写一个程序，找出这三个正序数组的中位数。
// 算法的时间复杂度应该为 O(log (m+n+p)) ，其中m、n、p分别为三个数组的长度。
// 示例1
// 输入：
// [1,3],[2],[4,5]
// 复制
// 返回值：
// 3.00000
// 复制
// 说明：
// 合并数组 = [1,2,3,4,5] ，中位数 3
// 备注：
// herd1.length == m
// herd2.length == n
// herd3.length == p
// 0 <= m,n,p <= 1000
// 1 <= m + n + p <= 3000
// 0 <= herd1[i], herd2[i], herd3[i] <= 10000

import java.util.*;

public class Solution {
    /**
     * Find the median of three sorted arrays.
     *
     * @param herd1 int[] - first sorted array
     * @param herd2 int[] - second sorted array
     * @param herd3 int[] - third sorted array
     * @return double - the median value
     */
    public double findMedianSortedArray(int[] herd1, int[] herd2, int[] herd3) {
        int totalLen = herd1.length + herd2.length + herd3.length;
        if (totalLen == 0) return 0.0; // Edge case: all arrays are empty
        
        int k1 = (totalLen + 1) / 2;
        int k2 = (totalLen + 2) / 2;
        
        int median1 = findKth(herd1, herd2, herd3, k1);
        int median2 = findKth(herd1, herd2, herd3, k2);
        
        return (median1 + median2) / 2.0;
    }
    
    private int findKth(int[] A, int[] B, int[] C, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        
        if (A.length > 0) {
            low = Math.min(low, A[0]);
            high = Math.max(high, A[A.length - 1]);
        }
        if (B.length > 0) {
            low = Math.min(low, B[0]);
            high = Math.max(high, B[B.length - 1]);
        }
        if (C.length > 0) {
            low = Math.min(low, C[0]);
            high = Math.max(high, C[C.length - 1]);
        }
        
        // Edge case when all arrays are empty
        if (low > high) return 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = countLessOrEqual(A, mid) + countLessOrEqual(B, mid) + countLessOrEqual(C, mid);
            
            if (count >= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private int countLessOrEqual(int[] A, int val) {
        int left = 0;
        int right = A.length - 1;
        
        // Binary search to find the count of elements less than or equal to val
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
