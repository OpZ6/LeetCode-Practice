//279. Perfect Squares
//https://leetcode.com/problems/perfect-squares/

//DP
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++)
            dp[i] = Integer.MAX_VALUE;
        return topDown(n, dp);
    }

    public int numSquaresBottomUp(int n, int[] dp) {
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++)
            for (int j = 1; j <= Math.floor(Math.sqrt(i)); j++) 
                dp[i] = Math.min(1 + dp[i - j*j], dp[i]);
        
        return dp[n];
    }
    public int topDown(int n, int[] dp) {
        if(n == 0)
            dp[n] = 0;
        for(int i = 1; i <= Math.floor(Math.sqrt(n)); i++){
            if(dp[n - i*i] == Integer.MAX_VALUE)
                dp[n] = Math.min(1 + topDown(n - i*i, dp), dp[n]);
            else
                dp[n] = Math.min(1 + dp[n - i*i], dp[n]);
        }
        return dp[n];
    }
}

//faster
class Solution {
    public int numSquares(int n) {
        int[] sqReq = new int[n + 1];
        Arrays.fill(sqReq, Integer.MAX_VALUE);
        
        sqReq[0] = 0;
        
        for (int currNum = 1; currNum <= n; currNum++) {
            for (int prevNum = 1; prevNum * prevNum <= currNum; prevNum++) {
                int prevStateContri = sqReq[currNum - prevNum * prevNum];
                sqReq[currNum] = Math.min(sqReq[currNum], prevStateContri + 1);
            }
        }
        return sqReq[n];
    }
}
