//733. Flood Fill
//https://leetcode.com/problems/flood-fill/description/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int target = image[sr][sc];
        if (target == color)
            return image; // No need to fill if the color is already the same
        int row = image.length;
        int col = image[0].length;
        int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        Set<int[]> visited = new HashSet<>();
        helper(image, row, col, sr, sc, color, target, move, visited);
        return image;
    }

    private void helper(int[][] image, int row, int col, int sr, int sc, int color, int target, int[][] move, Set<int[]> visited) {
        if (sr < 0 || sr >= row || sc < 0 || sc >= col || image[sr][sc] != target || visited.contains(new int[] { sr, sc })) {
            return;
        }

        visited.add(new int[] { sr, sc });
        image[sr][sc] = color;

        for (int[] m : move) {
            helper(image, row, col, sr + m[0], sc + m[1], color, target, move, visited);
        }
    }
}

//easier
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int target = image[sr][sc];
        if (target == color) return image; // No need to fill if the color is already the same
        int row = image.length;
        int col = image[0].length;
        int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};

        helper(image, row, col, sr, sc, color, target, move);
        return image;
    }

    private void helper(int[][] image, int row, int col, int sr, int sc, int color, int target, int[][] move){
        if (sr < 0 || sr >= row || sc < 0 || sc >= col || image[sr][sc] != target) {
            return;
        }

        image[sr][sc] = color; // Mark the cell with the new color

        for (int[] m : move) {
            helper(image, row, col, sr + m[0], sc + m[1], color, target, move);
        }
    }
}

