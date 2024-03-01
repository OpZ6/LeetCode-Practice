//286. Walls and Gates
//https://leetcode.com/problems/walls-and-gates/description/

class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        Queue<int[]> queue = new LinkedList<>();

        int row = rooms.length;
        int col = rooms[0].length;
        int[] move = new int[]{1, 0, -1, 0, 1};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int dis = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                int[] tmp = queue.poll();
                for (int _i = 0; _i < 4; _i++) {
                    int x = tmp[0] + move[_i];
                    int y = tmp[1] + move[_i + 1];
                    int[] tmplist = new int[]{x,y};
                    if (0 <= x && x < row && 0 <= y && y < col && rooms[x][y] == 2147483647) {
                        queue.offer(tmplist);
                        if (dis < rooms[x][y]) {
                            rooms[x][y] = dis;
                        }
                    }
                }
            }
            dis += 1;
        }
    }
}

//benchmark
//DFS
class Solution {
    private static int[] d = { 0, 1, 0, -1, 0 };

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0)
                    dfs(rooms, i, j);
    }

    public void dfs(int[][] rooms, int i, int j) {
        for (int k = 0; k < 4; ++k) {
            int p = i + d[k], q = j + d[k + 1];
            if (0 <= p && p < rooms.length && 0 <= q && q < rooms[0].length && rooms[p][q] > rooms[i][j] + 1) {
                rooms[p][q] = rooms[i][j] + 1;
                dfs(rooms, p, q);
            }
        }
    }
}

//multi start point BFS
class Solution {
    public static final int[] d = { 0, 1, 0, -1, 0 };

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0)
            return;
        int m = rooms.length, n = rooms[0].length;

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (rooms[i][j] == 0)
                    queue.offer(i * n + j); // Put gates in the queue

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int i = x / n, j = x % n;
            for (int k = 0; k < 4; ++k) {
                int p = i + d[k], q = j + d[k + 1]; // empty room
                if (0 <= p && p < m && 0 <= q && q < n && rooms[p][q] == Integer.MAX_VALUE) {//rooms[p][q] different for two BFS
                    rooms[p][q] = rooms[i][j] + 1;
                    queue.offer(p * n + q);
                }
            }
        }
    }
}

//BFS
class Solution {
    public static final int[] d = { 0, 1, 0, -1, 0 };

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0)
            return;
        for (int i = 0; i < rooms.length; ++i)
            for (int j = 0; j < rooms[0].length; ++j)
                if (rooms[i][j] == 0)
                    bfs(rooms, i, j);
    }

    private void bfs(int[][] rooms, int i, int j) {
        int m = rooms.length, n = rooms[0].length;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(i * n + j); // Put gate in the queue
        while (!queue.isEmpty()) {
            int x = queue.poll();
            i = x / n;
            j = x % n;
            for (int k = 0; k < 4; ++k) {
                int p = i + d[k], q = j + d[k + 1];
                if (0 <= p && p < m && 0 <= q && q < n && rooms[p][q] > rooms[i][j] + 1) {
                    rooms[p][q] = rooms[i][j] + 1;
                    queue.offer(p * n + q);
                }
            }
        }
    }
}
