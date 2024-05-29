//378. Kth Smallest Element in a Sorted Matrix
//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

//Heap
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] rows : matrix) {
            for (int i : rows) {
                minHeap.offer(i);
            }
        }

        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}

//binary search for kth element
/**
 * Using Binary Search on the range of Min and Max values in the matrix
 *
 * Time Complexity: O(2*N * log(Max-Min))
 *
 * Space Complexity: O(1)
 *
 * N = Length of one side of the matrix. Max = Maximum value in Matrix. Min = Minimum value in matrix.
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = countLessEqual(matrix, mid, n);
            if (count < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return start;
    }
    
    private int countLessEqual(int[][] matrix, int val, int n) {
        int count = 0;
        int row = n - 1;
        int col = 0;
        
        while (row >= 0 && col < n) {
            if (matrix[row][col] > val) {
                row--;
            } else {
                count += row + 1;
                col++;
            }
        }
        
        return count;
    }
}
