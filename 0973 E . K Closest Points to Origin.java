//973. K Closest Points to Origin
//https://leetcode.com/problems/k-closest-points-to-origin/description/

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> points[a][0]*points[a][0] + points[a][1]*points[a][1] - points[b][0]*points[b][0] - points[b][1]*points[b][1] );

        // 使用Lambda表达式定义比较器，比较点到原点的距离平方
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingDouble(a -> points[a][0] * points[a][0] + points[a][1] * points[a][1]));
      
        for (int i = 0; i < points.length; i++) {
            minHeap.offer(i);
        }

        int[][] res = new int[k][2];
        
        for (int i = 0; i < k; i++) {
            res[i] = points[minHeap.poll()];
        }

        return res;
    }
}
