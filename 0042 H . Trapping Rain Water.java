//42. Trapping Rain Water
//https://leetcode.com/problems/trapping-rain-water/description/

//单调栈
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] >= height[st.peek()]) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int heightH = Math.min(height[left], height[i]) - bottomH;
                ans += heightH * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }
}
