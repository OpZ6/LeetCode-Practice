//1137. N-th Tribonacci Number
//https://leetcode.com/problems/n-th-tribonacci-number/description/

//mine
class Solution {
    public int tribonacci(int n) {
        int[] num = new int[Math.max(3,n + 1)];
        num[0] = 0;
        num[1] = 1;
        num[2] = 1;
        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                num[i] = num[i - 1] + num[i - 2] + num[i - 3];
            }
        }

        return num[n];
    }
}

//hashmap
public class Solution {
    private Map<Integer, Integer> dp = new HashMap<>(){{
            put(0, 0);
            put(1, 1);
            put(2, 1);
    }};
    
    private int dfs(int i) {
        if (dp.containsKey(i)) {
            return dp.get(i);
        }
        
        int answer = dfs(i - 1) + dfs(i - 2) + dfs(i - 3);
        dp.put(i, answer);
        return answer;
    }
    
    public int tribonacci(int n) {
        return dfs(n);
    }
}

class Solution {
    public int tribonacci(int n) {
        if (n < 3) {
            return n > 0 ? 1 : 0;
        }
        
        int a = 0, b = 1, c = 1;
        for (int i = 0; i < n - 2; ++i) {
            int tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
        }
        
        return c;
    }
}
