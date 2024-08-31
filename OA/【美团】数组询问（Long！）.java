// 小美的数组询问
// 小美拿到了一个由正整数组成的数组，但其中有一些元素是未知的（用 0 来表示）。
// 现在小美想知道，如果那些未知的元素在区间[l,r]范围内随机取值的话，数组所有元素之和的最小值和最大值分别是多少？
// 共有q次询问。

// 输入描述：
// 第一行输入两个正整数n,q，代表数组大小和询问次数。
// 第二行输入n个整数a_i，其中如果输入的a_i为 0，那么说明a_i是未知的。
// 接下来的q行，每行输入两个正整数 l,r，代表一次询问。
// 1\leq n,q \leq 10^5
// 0 \leq a_i \leq 10^9
// 1\leq l \leq r \leq 10^9
// 输出描述：
// 输出q行，每行输出两个正整数，代表所有元素之和的最小值和最大值。
// 示例1
// 输入例子：
// 3 2
// 1 0 3
// 1 2
// 4 4
// 输出例子：
// 5 6
// 8 8
// 例子说明：
// 只有第二个元素是未知的。
// 第一次询问，数组最小的和是 1+1+3=5，最大的和是 1+2+3=6。
// 第二次询问，显然数组的元素和必然为 8。

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();  // 数组大小
        int q = in.nextInt();  // 询问次数

        long sum = 0;  // 用于存储已知元素的总和
        int count0 = 0;  // 用于存储未知元素（即0）的数量
        int[] array = new int[n];  // 用于存储输入数组

        // 读取数组并计算已知元素的总和和未知元素的数量
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
            if (array[i] == 0) {
                count0++;
            } else {
                sum += array[i];
            }
        }

        // 处理每次询问
        for (int i = 0; i < q; i++) {
            int l = in.nextInt();  // 读取 l
            int r = in.nextInt();  // 读取 r
            long minSum = sum + count0 * (long)l;  // 计算最小和
            long maxSum = sum + count0 * (long)r;  // 计算最大和
            System.out.println(minSum + " " + maxSum);  // 输出结果
        }
    }
}
