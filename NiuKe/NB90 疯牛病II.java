//NB90 疯牛病II
//https://www.nowcoder.com/practice/2d5c96e452a949e09d98bb32aec3b61d?tpId=354&tqId=10595829&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 在一个m x n的牧场中，遭受了疯牛病的袭击，有部分的牛感染了病毒。

// 现在，牧场中每个单元格可以有以下三个值之一：

// 值0代表空地；
// 值1代表健康的牛；
// 值2代表患有疯牛病的牛。
// 每分钟，患有疯牛病的牛周围4个方向上相邻的健康牛都会被感染。

// 返回直到牧场中没有健康的牛为止所必须经过的最小分钟数。如果不可能到达此情况，返回-1。
// 示例1
// 输入：
// [[2,1,1],[0,1,1],[1,0,1]]
// 复制
// 返回值：
// -1
// 复制
// 说明：
// 左下角的牛（第 2 行， 第 0 列）永远不会生病，因为生病只会发生在 4 个正向上。
// 示例2
// 输入：
// [[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,2]]
// 复制
// 返回值：
// 6
// 复制
// 备注：
// m == pasture.length
// n == pasture[i].length
// 1 <= m, n <= 10
// pasture[i][j] 仅为0、1或2

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pasture int整型二维数组
     * @return int整型
     */
    public int healthyCowsII (int[][] pasture) {
        // write code here
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//扩散方向
        int m = pasture.length;
        int n = pasture[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        
        //遍历表格把2的格全部加入队列等待后续扩散操作
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pasture[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        //开始扩散
        boolean flag = true;
        int time = 0;
        while (flag) {
            time++;//记录时间
            flag = false;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int[] dir : dirs) {
                    int x = poll[0] + dir[0];
                    int y = poll[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && pasture[x][y] == 1) {
                        flag = true;//存在牛被感染，说明扩散还没结束
                        pasture[x][y] = 2;
                        queue.offer(new int[] {x, y});
                    }
                }
            }
        }

        //检查是否还有存在无法感染的牛
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pasture[i][j] == 1) {
                    return -1;
                }
            }
        }
        return time - 1;
    }
}
