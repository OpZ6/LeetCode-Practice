//542. 01 Matrix
//

//for distance question, no DFS, use BFS (with queue)
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Initialize the queue with all the positions of the zero cells
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    mat[i][j] = Integer.MAX_VALUE; // Set to max value to indicate unvisited
                }
            }
        }

        // Direction vectors for up, down, left, right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // BFS to update the distance of each cell
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : dirs) {
                int r = cell[0] + dir[0];
                int c = cell[1] + dir[1];
                if (r >= 0 && r < row && c >= 0 && c < col && mat[r][c] == Integer.MAX_VALUE) {
                    mat[r][c] = mat[cell[0]][cell[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }

        return mat;
    }
}
