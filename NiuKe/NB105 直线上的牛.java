//NB105 直线上的牛
//https://www.nowcoder.com/practice/58ff4047d6194d2ca4d80869f53fa6ec?tpId=354&tqId=10595661&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 农场里有很多头牛，每头牛的位置可以用一个二维平面上的点表示，其中points[i] = [xi, yi] 表示第i头牛的位置。现在农场主想要在农场里修建一条直线，使得尽可能多的牛都在这条直线上，你需要计算出最多有多少头牛在同一条直线上。
// 示例1
// 输入：
// [[1,1],[2,2],[4,4]]
// 复制
// 返回值：
// 3
// 复制
// 示例2
// 输入：
// [[1,1],[1,2],[1,3]]
// 复制
// 返回值：
// 3
// 复制
// 示例3
// 输入：
// [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
// 复制
// 返回值：
// 4
// 复制
// 说明：
// 可以在(4,1),(3,2),(1,4),(2,3)之间画一条直线，所以最多有4头牛在同一条直线上。


// 备注：
// 1 <= points.length <= 300
// points[i].length == 2
// -10^4 <= xi, yi <= 10^4
// points 中的所有点 互不相同

import java.util.*;

public class Solution {
    public int maxPoints(int[][] points) {
        if (points.length < 3) return points.length;

        int n = points.length;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int duplicate = 1;  // 统计和当前点重合的点
            int localMax = 0;    // 记录经过当前点的最大点数

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;  // 和当前点重合
                    continue;
                }

                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                // 使用分数形式的斜率作为键
                String slope = dx + "/" + dy;
                map.put(slope, map.getOrDefault(slope, 0) + 1);

                localMax = Math.max(localMax, map.get(slope));
            }

            maxCount = Math.max(maxCount, localMax + duplicate);
        }

        return maxCount;
    }

    // 求两个数的最大公约数
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
