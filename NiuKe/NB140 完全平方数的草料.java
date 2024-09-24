//https://www.nowcoder.com/practice/0d467680d82046db866cf89beb861144?tpId=354&tqId=10595846&ru=/exam/oj&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Foj

// 描述
// 农场的牛只有特殊的生活习惯，它们每天需要摄取一定数量的草料，而且这些草料必须是完全平方数的数量。例如，一头牛可以吃1、4、9或16份草料，但不能吃3或11份。现在，农场主人需要你的帮助来确定，给定一个整数n，表示一头牛一天需要摄取的草料数量，你需要返回一个数组，数组中的元素是摄取草料的完全平方数，且数组的和等于n，数组中的元素需要按照非递减的顺序排列，你需要返回的数组中元素的个数应该尽可能少，同时因为牛牛偏好一次吃较多的草料，所以如果有多个答案请返回最大完全平方数较大的一组。
// 示例1
// 输入：
// 12
// 复制
// 返回值：
// [4,4,4]
// 复制
// 说明：
// 12 = 4 + 4 + 4
// 示例2
// 输入：
// 13
// 复制
// 返回值：
// [4,9]
// 复制
// 备注：
// 1 <= n <= 10^4

import java.util.*;

public class Solution {
    public int[] numSquares(int n) {
        // 创建 dp 数组，初始化为一个较大的值（表示不可能）
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // 初始化 dp[0] = 0，表示为 0 不需要任何平方数
        dp[0] = 0;
        
        // 动态规划，计算每个 dp[i] 的值
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        // 回溯寻找组成 n 的所有完全平方数
        List<Integer> result = new ArrayList<>();
        int remaining = n;
        
        while (remaining > 0) {
            for (int j = (int) Math.sqrt(remaining); j > 0; j--) {
                int square = j * j;
                // 如果 dp[remaining] 是由 dp[remaining - square] + 1 得到的
                if (dp[remaining] == dp[remaining - square] + 1) {
                    result.add(square);
                    remaining -= square;
                    break;
                }
            }
        }
        
        // 将结果转换为数组并返回
        Collections.reverse(result); // 确保返回的结果是非递减顺序
        int[] resArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArray[i] = result.get(i);
        }
        return resArray;
    }
}
