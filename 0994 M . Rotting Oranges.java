//994. Rotting Oranges
//https://leetcode.com/problems/rotting-oranges/description/

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        
        // Step 1: Add all rotten oranges to the queue and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        
        // If there are no fresh oranges, return 0
        if (freshOranges == 0) {
            return 0;
        }
        
        // Step 2: BFS traversal to rot adjacent fresh oranges
        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false;
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                
                // Check all 4 directions
                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    
                    // If the new cell is within bounds and is a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        // Rot the fresh orange
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        freshOranges--;
                        rotted = true;
                    }
                }
            }
            
            if (rotted) {
                minutes++;
            }
        }
        
        // If there are still fresh oranges left, return -1
        return freshOranges == 0 ? minutes : -1;
    }
}
