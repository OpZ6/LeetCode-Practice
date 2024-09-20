// //https://www.nowcoder.com/practice/05abc133293a42358bbbb4016034728e?tpId=354&tqId=10594522&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 在一个牧场中，有 numCows 头牛，编号为 0 到 numCows - 1。牧场主为了方便管理，记录了牛群之间的喂养顺序关系。喂养顺序关系用一个数组 feedOrders 给出，其中 feedOrders[i] = [ai, bi]，表示如果要喂养牛 ai，则必须先喂养牛 bi。

// 例如，喂养顺序对 [0, 1] 表示：想要喂养牛 0，你需要先喂养牛 1。
// 返回你为了喂养完所有牛所安排的喂养顺序，不会出现多个答案的情况。如果不可能喂养完所有牛，返回一个空数组 。

// 示例1
// 输入：
// 2,[[1,0]]
// 复制
// 返回值：
// [0,1]
// 复制
// 说明：
// 总共有 2 头牛。要喂养牛 1，你需要先喂养牛 0。因此，正确的喂养顺序为 [0,1] 。


// 备注：
// 1 <= numCows <= 2000
// 0 <= feedOrders.length <= numCows * (numCows - 1)
// feedOrders[i].length == 2
// 0 <= ai, bi < numCows
// ai != bi
// 所有[ai, bi] 互不相同

;import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param numCows int整型
     * @param feedOrders int整型二维数组
     * @return int整型一维数组
     */

    public int[] findFeedOrderII(int numCows, int[][] feedOrders) {
        // 创建一个邻接表
        List<List<Integer>> graph = new ArrayList<>();
        // 入度数组
        int[] inDegree = new int[numCows];
        
        // 初始化图
        for (int i = 0; i < numCows; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 填充图和入度数组
        for (int[] order : feedOrders) {
            int ai = order[0];
            int bi = order[1];
            graph.get(bi).add(ai); // 喂养 bi 后可以喂养 ai
            inDegree[ai]++;        // ai 的入度加 1
        }
        
        // 使用队列进行广度优先搜索（拓扑排序）
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为 0 的牛加入队列，表示它们可以最先喂养
        for (int i = 0; i < numCows; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 结果列表
        List<Integer> result = new ArrayList<>();

        // 进行拓扑排序
        while (!queue.isEmpty()) {
            int cow = queue.poll();
            result.add(cow); // 喂养当前的牛
            
            // 遍历当前牛可以喂养的其他牛（它们的入度减 1）
            for (int next : graph.get(cow)) {
                inDegree[next]--;
                // 如果某头牛的入度变为 0，表示可以喂养
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // 如果所有牛都喂养了，返回结果；否则，返回空数组
        if (result.size() == numCows) {
            // 将结果转换为数组
            return result.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[0]; // 表示不可能完成任务
        }
    }
}
