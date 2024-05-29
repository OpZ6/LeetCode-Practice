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
        if (matrix == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int n = matrix.length;
        if (k > n * n) {
            throw new NoSuchElementException("k is greater than number of elements in matrix");
        }
        if (k == 1) {
            return matrix[0][0];
        }
        if (k == n * n) {
            return matrix[n - 1][n - 1];
        }

        int start = matrix[0][0];
        int end = matrix[n - 1][n - 1];

        while (start < end) {
            int mid = start + (end - start) / 2;
            int[] smallLargePair = { start, end };
            int count = countLessThanAndEqual(matrix, mid, smallLargePair);
            if (count == k) {
                return smallLargePair[0];
            }
            if (count < k) {
                start = smallLargePair[1]; // search higher
            } else {
                end = smallLargePair[0]; // search lower
            }
        }

        return start;
    }

    private int countLessThanAndEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length;
        int row = 0;
        int col = n - 1;
        while (row < n && col >= 0) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                col--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                row++;
                count += col + 1;
            }
        }
        return count;
    }
}
