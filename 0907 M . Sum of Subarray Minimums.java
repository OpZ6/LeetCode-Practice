//907. Sum of Subarray Minimums
//https://leetcode.com/problems/sum-of-subarray-minimums/

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        int n = arr.length;

        Stack<Integer> stack = new Stack<>();
        long result = 0; // Use long to prevent overflow

        // prevSmaller[i] stores index of previous smaller element
        int[] prevSmaller = new int[n];
        // nextSmaller[i] stores index of next smaller or equal element
        int[] nextSmaller = new int[n];

        // Initialize prevSmaller
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Initialize nextSmaller
        for (int i = 0; i < n; i++) {
            // Changed '>' to '>=' to handle duplicates correctly
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            nextSmaller[stack.pop()] = n;
        }

        // Calculate each element's contribution
        for (int i = 0; i < n; i++) {
            long leftCount = i - prevSmaller[i];
            long rightCount = nextSmaller[i] - i;
            long contribution = (leftCount * rightCount % MOD) * arr[i] % MOD;
            result = (result + contribution) % MOD; // Ensure each addition is modulo MOD
        }

        return (int) result; // Convert back to int before returning
    }
}

class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 方便赋值 left
        for (int i = 0; i < n; ++i) {
            while (st.size() > 1 && arr[st.peek()] >= arr[i])
                right[st.pop()] = i; // i 恰好是栈顶的右边界
            left[i] = st.peek();
            st.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; ++i)
            ans += (long) arr[i] * (i - left[i]) * (right[i] - i); // 累加贡献
        return (int) (ans % MOD);
    }
}

class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int sumSubarrayMins(int[] arr) {
        long ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 哨兵
        for (int r = 0; r <= arr.length; ++r) {
            int x = r < arr.length ? arr[r] : -1; // 假设 arr 末尾有个 -1
            while (st.size() > 1 && arr[st.peek()] >= x) {
                int i = st.pop();
                ans += (long) arr[i] * (i - st.peek()) * (r - i); // 累加贡献
            }
            st.push(r);
        }
        return (int) (ans % MOD);
    }
}
