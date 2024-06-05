//52. N-Queens II
//https://leetcode.com/problems/n-queens-ii/editorial/

//backtracking
class Solution {
    private int size;

    public int totalNQueens(int n) {
        size = n;
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtrack(
            int row,
            Set<Integer> diagonals,
            Set<Integer> antiDiagonals,
            Set<Integer> cols) {
        // Base case - N queens have been placed
        if (row == size) {
            return 1;
        }

        int solutions = 0;
        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;
            // If the queen is not placeable
            if (cols.contains(col) ||
                    diagonals.contains(currDiagonal) ||
                    antiDiagonals.contains(currAntiDiagonal)) {
                continue;
            }

            // "Add" the queen to the board
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);

            // Move on to the next row with the updated board state
            solutions += backtrack(row + 1, diagonals, antiDiagonals, cols);

            // "Remove" the queen from the board since we have already
            // explored all valid paths using the above function call
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
        }

        return solutions;
    }
}

//主要思路
列数组 (cols)：用于记录哪些列已经有皇后了。
主对角线数组 (d1)：用于记录哪些主对角线上已经有皇后了。
副对角线数组 (d2)：用于记录哪些副对角线上已经有皇后了。
我们通过逐行放置皇后，并使用布尔数组记录哪些列和对角线已经被占据。如果当前列或对角线被占据，则跳过当前列。

代码解释
java
复制代码
public class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];     // 列 |
        boolean[] d1 = new boolean[2 * n];   // 主对角线 \
        boolean[] d2 = new boolean[2 * n];   // 副对角线 /
        backtracking(0, cols, d1, d2, n);
        return count;
    }

    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int id1 = col - row + n; // 主对角线的索引
            int id2 = col + row;     // 副对角线的索引
            if (cols[col] || d1[id1] || d2[id2]) {
                continue;
            }

            cols[col] = true; 
            d1[id1] = true; 
            d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; 
            d1[id1] = false; 
            d2[id2] = false;
        }
    }
}
详细解释
初始化
java
复制代码
boolean[] cols = new boolean[n];     // 列 |
boolean[] d1 = new boolean[2 * n];   // 主对角线 \
boolean[] d2 = new boolean[2 * n];   // 副对角线 /
这三个布尔数组分别用于记录列和两个对角线的占用情况。

回溯函数
java
复制代码
public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
    if (row == n) {
        count++;
        return;
    }

    for (int col = 0; col < n; col++) {
        int id1 = col - row + n; // 主对角线的索引
        int id2 = col + row;     // 副对角线的索引
        if (cols[col] || d1[id1] || d2[id2]) {
            continue;
        }

        cols[col] = true; 
        d1[id1] = true; 
        d2[id2] = true;
        backtracking(row + 1, cols, d1, d2, n);
        cols[col] = false; 
        d1[id1] = false; 
        d2[id2] = false;
    }
}

// id1和id2的解释
// 主对角线 (d1)：在棋盘上，主对角线是从左上到右下的斜线。对于任何一个位置 (row, col)，它所在的主对角线的索引可以表示为 col - row。为了避免负数索引，统一加上 n 进行偏移：id1 = col - row + n。
// 例如，对于位置 (2, 3)，id1 = 3 - 2 + n = 1 + n。

// 副对角线 (d2)：在棋盘上，副对角线是从右上到左下的斜线。对于任何一个位置 (row, col)，它所在的副对角线的索引可以表示为 col + row。
// 例如，对于位置 (2, 3)，id2 = 3 + 2 = 5。

// 处理逻辑
// 检查冲突：在放置一个皇后之前，检查当前列和对角线是否已经有皇后。如果有冲突，则跳过当前列。

// if (cols[col] || d1[id1] || d2[id2]) {
//     continue;
// }
// 标记和递归：如果没有冲突，标记当前列和对角线，并递归地尝试在下一行放置皇后。

// cols[col] = true; 
// d1[id1] = true; 
// d2[id2] = true;
// backtracking(row + 1, cols, d1, d2, n);
// 回溯：在递归返回后，取消标记，以便尝试其他列。

// cols[col] = false; 
// d1[id1] = false; 
// d2[id2] = false;
// 总结
// 通过这种方法，我们利用布尔数组高效地记录和检测列及对角线的占用情况，避免了使用集合或其他数据结构，从而实现了快速的回溯算法。这样的方法能够在O(n!)的时间复杂度内解决N皇后问题。

//easier
public class Solution {
    int count = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \
        boolean[] d2 = new boolean[2 * n];   // diagonals /
        backtracking(0, cols, d1, d2, n);
        return count;
    }
    
    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if(row == n) count++;

        for(int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) continue;
            
            cols[col] = true; d1[id1] = true; d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
}
