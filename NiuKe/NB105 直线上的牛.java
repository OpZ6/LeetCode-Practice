//NB105 直线上的牛
//https://www.nowcoder.com/practice/58ff4047d6194d2ca4d80869f53fa6ec?tpId=354&tqId=10595661&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

import java.util.*;

import java.util.HashMap;

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
