// 小美的区间删除
// 小美拿到了一个大小为
// n
// n的数组，她希望删除一个区间后，使得剩余所有元素的乘积末尾至少有
// k
// k个 0。小美想知道，一共有多少种不同的删除方案？
// 时间限制：C/C++ 1秒，其他语言2秒
// 空间限制：C/C++ 256M，其他语言512M
// 输入描述：
// 第一行输入两个正整数n,k。
// 第二行输入n个正整数a_i，代表小美拿到的数组。
// 1\leq n,k \leq 10^5
// 1\leq a_i \leq 10^9
// 输出描述：
// 一个整数，代表删除的方案数。
// 示例1
// 输入例子：
// 5 2
// 2 5 3 4 20
// 输出例子：
// 4
// 例子说明：
// 第一个方案，删除[3]。
// 第二个方案，删除[4]。
// 第三个方案，删除[3,4]。
// 第四个方案，删除[2]。


//超时
import java.util.Scanner;

public class Main {
    // 计算x中包含多少个factor因子
    public static int countFactors(int x, int factor) {
        int count = 0;
        while (x % factor == 0 && x > 0) {
            count++;
            x /= factor;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 数组长度
        int k = scanner.nextInt(); // 至少需要的0的个数

        int[] array = new int[n];
        int[] countTwo = new int[n + 1]; // 存储因子2的前缀和
        int[] countFive = new int[n + 1]; // 存储因子5的前缀和

        // 读取输入并计算每个元素的2和5的因子个数
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            countTwo[i + 1] = countTwo[i] + countFactors(array[i], 2);
            countFive[i + 1] = countFive[i] + countFactors(array[i], 5);
        }

        long validSubarrays = 0;

        // 遍历每一个可能的删除区间
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int totalTwo = countTwo[n] - (countTwo[end + 1] - countTwo[start]);
                int totalFive = countFive[n] - (countFive[end + 1] - countFive[start]);
                if (totalTwo >= k && totalFive >= k) {
                    validSubarrays++;
                } else {
                    break;
                }
            }
        }

        System.out.println(validSubarrays);
        scanner.close();
    }
}

//二分查找
import java.io.*;
import java.util.*;

public class Main {
    static final int N = 100005;
    static int[] s2 = new int[N];
    static int[] s5 = new int[N];

    // 统计 x 中包含的 2 和 5 的个数
    static int[] count25(int x) {
        int c2 = 0, c5 = 0;
        while ((x & 1) == 0) {
            c2++;
            x >>= 1;
        }
        while (x % 5 == 0) {
            c5++;
            x /= 5;
        }
        return new int[]{c2, c5};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);

        String[] secondLine = reader.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(secondLine[i - 1]);
            int[] count = count25(x);
            s2[i] = count[0] + s2[i - 1];
            s5[i] = count[1] + s5[i - 1];
        }

        long res = 0;
        // 枚举每个以 l 为左边界的区间
        for (int l = 1; l <= n; l++) {
            int low = l, high = n;
            // 二分查找以 l 为左边界且满足条件的最大区间
            while (low < high) {
                int mid = (low + high + 1) / 2;
                if (check(l, mid, n, k, s2, s5))
                    high = mid - 1;
                else
                    low = mid;
            }
            int r = low;
            // 检查当前 r 是否满足条件
            if (!check(l, r, n, k, s2, s5))
                res += r - l + 1;
        }
        System.out.println(res);
    }

    // 检查区间 [l, r] 是否符合要求
    private static boolean check(int l, int r, int n, int k, int[] s2, int[] s5) {
        return (s2[r] - s2[l - 1] > s2[n] - k || s5[r] - s5[l - 1] > s5[n] - k);
    }
}
