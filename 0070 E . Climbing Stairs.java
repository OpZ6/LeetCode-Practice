//70. Climbing Stairs
//https://leetcode.com/problems/climbing-stairs/

class Solution {
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public int climbStairs(int N) {
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        int result;
        if (N < 3) {//0,1,2 important!!!
            result = N;
        } else {
            result = climbStairs(N - 1) + climbStairs(N - 2);
        }
        // keep the result in cache.
        cache.put(N, result);
        return result;
    }

}
