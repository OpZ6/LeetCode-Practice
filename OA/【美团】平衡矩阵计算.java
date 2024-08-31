// 小美的平衡矩阵
// 小美拿到了一个n∗n的矩阵，其中每个元素是 0 或者 1。
// 小美认为一个矩形区域是完美的，当且仅当该区域内 0 的数量恰好等于 1 的数量。
// 现在，小美希望你回答有多少个i∗i的完美矩形区域。你需要回答1≤i≤n的所有答案。
// 时间限制：C/C++ 1秒，其他语言2秒
// 空间限制：C/C++ 256M，其他语言512M
// 输入描述：
// 第一行输入一个正整数n，代表矩阵大小。
// 接下来的n行，每行输入一个长度为n的 01 串，用来表示矩阵。
// 输出描述：
// 输出n行，第i行输出i*i的完美矩形区域的数量。
// 示例1
// 输入例子：
// 4
// 1010
// 0101
// 1100
// 0011
// 输出例子：
// 0
// 7
// 0
// 1

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine(); // 消耗换行符

        // 初始化矩阵
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = in.nextLine().toCharArray();
        }

        // 初始化前缀和矩阵
        int[][] prefixZero = new int[n + 1][n + 1];
        int[][] prefixOne = new int[n + 1][n + 1];

        // 计算前缀和
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefixZero[i][j] = prefixZero[i - 1][j] + prefixZero[i][j - 1] - prefixZero[i -1][j - 1];
                prefixOne[i][j] = prefixOne[i - 1][j] + prefixOne[i][j - 1] - prefixOne[i - 1][j- 1];

                if (matrix[i - 1][j - 1] == '0') {
                    prefixZero[i][j]++;
                } else {
                    prefixOne[i][j]++;
                }
            }
        }

        // 计算每个 i * i 的完美矩形数量
        for (int size = 1; size <= n; size++) {
            int count = 0;
            for (int i = size; i <= n; i++) {
                for (int j = size; j <= n; j++) {
                    int totalZeros = prefixZero[i][j] - prefixZero[i - size][j] - prefixZero[i][j -size] + prefixZero[i - size][j - size];
                    int totalOnes = prefixOne[i][j] - prefixOne[i - size][j] - prefixOne[i][j -size] + prefixOne[i - size][j - size];

                    if (totalZeros == totalOnes) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}

// 前缀和矩阵构建：

// prefixZero[i][j] 表示从 (0,0) 到 (i-1,j-1) 范围内的 0 的数量。
// prefixOne[i][j] 表示从 (0,0) 到 (i-1,j-1) 范围内的 1 的数量。
// 前缀和的计算公式考虑了累加当前单元格的值，并减去多余部分。
// 计算子矩形的 0 和 1 的数量：

// 使用前缀和矩阵，在 O(1) 时间内计算任意 i*i 子矩形的 0 和 1 的数量。
// 如果 0 和 1 的数量相等，则该子矩形是完美的。
// 时间复杂度：

// 前缀和矩阵的构建是 O(n^2)。
// 子矩形数量的计算为 O(n^4)，但由于每个子矩形的查询是 O(1)，相较于原来的暴力 O(n^5)，这是一个显著的优化。
