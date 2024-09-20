// https://www.nowcoder.com/practice/f6ab3d7e20f54860886848f0a6374987?tpId=354&tqId=10595836&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 在一个牧场中，有n头牛，牛的品种分为k种，用0到k-1的整数表示。现在需要对牛群进行排序，使得相同品种的牛相邻，并按照0到k-1的顺序排列。

// 请你在不使用库内置的sort函数的情况下解决这个问题。

// 示例1
// 输入：
// [3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0],4
// 复制
// 返回值：
// [0,0,0,0,0,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3]
// 复制
// 示例2
// 输入：
// [2,0,2,1,1,0],3
// 复制
// 返回值：
// [0,0,1,1,2,2]
// 复制
// 备注：
// n == cows.length
// 1 <= n <= 3000
// cows[i] 为 0 到 k-1 的整数
// 1 <= k <= 3000

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param cows int整型一维数组
     * @param k int整型
     * @return int整型一维数组
     */
    public int[] sortCowsIII (int[] cows, int k) {
        // 创建一个计数数组，用来记录每个品种的牛的数量
        int[] count = new int[k];

        // 统计每个品种的牛的数量
        for (int cow : cows) {
            count[cow]++;
        }

        // 创建结果数组
        int[] result = new int[cows.length];
        int index = 0;

        // 按照品种顺序将牛填入结果数组
        for (int i = 0; i < k; i++) {
            int num = count[i];  // 品种 i 的牛的数量
            while (num > 0) {
                result[index++] = i;  // 填入品种 i
                num--;
            }
        }

        return result;
    }
}
