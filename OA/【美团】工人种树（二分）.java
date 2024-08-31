// 种树问题
// 需要工人种树，每个工人种树的间隔一致，工人只会往右种树，如果遇到重合不会重复种树，如果需要工人种树间隔达到最小情况而满足总种树需求，请问最小间隔是多少 例如 3 6 1 2 5 代表有三个工人，一共要种6棵，工人位置在125三个位置上，每个工人最少需要种植3棵树，因为1种123，2种234，5种567，所以最后是1234567一共7棵，大于6了，如果只种2课则最后是5，小于6不可以。  
// 理解了新的规则之后，问题的解法需要调整：每个工人都必须种树，但如果他们的种树位置与其他工人种过的树重叠，则不重复种植。我们依然可以通过二分查找来寻找最小的间隔，但在检查每个间隔时，需要考虑已经种植过的位置，以确保不重复计算。
// 调整后的步骤：
// 1. 排序工人位置：仍然需要先对工人位置进行排序。
// 2. 二分查找最小间隔：用二分法尝试不同的间隔，检查是否能种出所需数量的树。
// 3. 更新种树逻辑：在每次检查间隔时，更新种植位置并避免重复计数。
// 调整后的代码实现
import java.util.Arrays;
import java.util.Scanner;

public class TreePlanting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int workers = scanner.nextInt(); // 工人数
        int requiredTrees = scanner.nextInt(); // 需要种植的树数

        int[] positions = new int[workers];
        for (int i = 0; i < workers; i++) {
            positions[i] = scanner.nextInt();
        }

        Arrays.sort(positions);

        int left = 1;
        int right = positions[positions.length - 1] - positions[0];
        int result = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlantAllTrees(positions, requiredTrees, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canPlantAllTrees(int[] positions, int requiredTrees, int interval) {
        int totalPlanted = 0; // 记录总共种植的树数
        int currentEnd = -1; // 当前已种植的最后一个位置

        for (int position : positions) {
            int start = Math.max(position, currentEnd + 1); // 每个工人的种植起点

            // 如果此工人的种植超出规定间隔范围，直接返回false
            if (start + interval - 1 >= position + interval * (requiredTrees - totalPlanted)) {
                return false;
            }

            // 更新已种植的最后一个位置和总种植数
            currentEnd = start + interval - 1;
            totalPlanted += currentEnd - start + 1;

            // 如果种植的树数已经满足需求，直接返回true
            if (totalPlanted >= requiredTrees) {
                return true;
            }
        }

        return totalPlanted >= requiredTrees;
    }
}
// 代码解释：
// ● start = Math.max(position, currentEnd + 1)：每个工人的种植起点是他们的位置或者最后一个种植位置的下一个位置，以防止重复种植。
// ● currentEnd = start + interval - 1：计算当前工人最终能够种植到的位置。
// ● 总树数的计算：如果已经种植的树数达到所需数量 requiredTrees，则返回 true，表示当前间隔是可行的。
// ● 二分查找：在最小和最大可能间隔中进行查找，直到找到最小的满足条件的间隔。
// 这样调整后的代码能确保每个工人都种树，并且不会在同一个位置重复种植树木。通过二分查找找到最小的间隔，使得总共种植的树木数量满足要求。
// ● 做题思路：从基础开始，直接写最简单计算函数（Brute Route），如果超时再进一步优化！！！
