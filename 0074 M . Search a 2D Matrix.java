//74. Search a 2D Matrix
//https://leetcode.com/problems/search-a-2d-matrix/description/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        //check max min
        if (matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
            return false;
        }

        //find row
        int up = 0;
        int down = row - 1;

        while (up <= down) {
            int mid = up + (down - up) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                up = mid + 1;
            }
            if (matrix[mid][0] > target) {
                down = mid - 1;
            }
        }

        // System.out.println(up + " " + down);

        //find col
        int left = 0;
        int right = col - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[down][mid] == target) {
                return true;
            }
            if (matrix[down][mid] < target) {
                left = mid + 1;
            }
            if (matrix[down][mid] > target) {
                right = mid - 1;
            }
        }

        return false;
    }
}


//better
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
