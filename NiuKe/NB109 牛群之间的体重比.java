//NB109 牛群之间的体重比
//https://www.nowcoder.com/practice/75717e3241704b55a71ecc6bdbf6b9b4?tpId=354&tqId=10594508&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 在一个牧场中，有很多牛。牧场主为了方便管理，记录了牛群之间的体重比关系。每头牛都有一个唯一的编号，用一个小写字母表示。体重比关系用一个变量对数组 weightRatios 和一个实数值数组 ratioValues 表示，其中 weightRatios[i] = [Ai, Bi] 和 ratioValues[i] 共同表示等式 Ai / Bi = ratioValues[i] 。每个 Ai 或 Bi 是一个表示单个牛的编号。

// 另有一些以数组 questions 表示的问题，其中 questions[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

// 返回所有问题的答案。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

// 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

// 示例1
// 输入：
// [["a","b"],["c","d"]],[2.0,3.0],[["a","c"],["b","d"],["a","d"],["c","a"]]
// 复制
// 返回值：
// [-1.00000,-1.00000,-1.00000,-1.00000]
// 复制
// 说明：
// a/b=2.0，c/d=3.0
// 但是仅凭这样无法计算a/c b/d a/d c/a
// 示例2
// 输入：
// [["a","b"],["b","c"],["c","d"]],[2.0,3.0,4.0],[["a","d"],["b","d"],["a","c"],["d","a"]]
// 复制
// 返回值：
// [24.00000,12.00000,6.00000,0.04167]
// 复制
// 说明：
// a/b = 2.0
// b/c = 3.0
// c/d = 4.0
// 则a/d = 24.0
// b/d = 12.0
// a/c = 6.0
// d/a = 1/24 = 0.04167
// 备注：
// 1 <= weightRatios.length <= 10
// weightRatios[i].length == 2
// Ai.length, Bi.length == 1
// ratioValues.length == weightRatios.length
// 0.0 < ratioValues[i] <= 30.0
// 1 <= questions.length <= 10
// questions[i].length == 2
// Cj.length, Dj.length == 1
// Ai, Bi, Cj, Dj 由小写英文字母与数字组成

import java.util.*;

public class Solution {
    // 使用哈希表来存储图，图的结构是 Map<String, Map<String, Double>>，每个节点连接的节点和对应的权重
    private Map<String, Map<String, Double>> graph = new HashMap<>();

    public double[] calcWeightRatios(String[][] weightRatios, double[] ratioValues, String[][] questions) {
        // 构建图
        for (int i = 0; i < weightRatios.length; i++) {
            String a = weightRatios[i][0];
            String b = weightRatios[i][1];
            double value = ratioValues[i];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());
            // 存储 a/b 和 b/a 的关系
            graph.get(a).put(b, value);
            graph.get(b).put(a, 1.0 / value);
        }

        // 处理每个问题
        double[] results = new double[questions.length];
        for (int i = 0; i < questions.length; i++) {
            String c = questions[i][0];
            String d = questions[i][1];
            if (!graph.containsKey(c) || !graph.containsKey(d)) {
                results[i] = -1.0;
            } else {
                // 使用 DFS 进行搜索
                Set<String> visited = new HashSet<>();
                results[i] = dfs(c, d, 1.0, visited);
            }
        }

        return results;
    }

    // 使用 DFS 进行搜索，寻找从 start 到 end 的路径
    private double dfs(String start, String end, double value, Set<String> visited) {
        if (start.equals(end)) {
            return value;
        }

        visited.add(start);
        // 遍历 start 的邻居节点
        for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
            String next = neighbor.getKey();
            if (!visited.contains(next)) {
                double result = dfs(next, end, value * neighbor.getValue(), visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }
        return -1.0; // 如果找不到路径，则返回 -1.0
    }
}
