//139. Word Break
//https://leetcode.com/problems/word-break/description/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 将 wordDict 转换为 HashSet，加快查找速度
        Set<String> wordDictSet = new HashSet(wordDict);
        
        // 创建 dp 数组，长度为 s.length() + 1
        boolean[] dp = new boolean[s.length() + 1];
        
        // 初始状态：空字符串可以被拼接
        dp[0] = true;
        
        // 遍历字符串的每个位置
        for (int i = 1; i <= s.length(); i++) {
            // 遍历从 0 到 i 的所有子字符串
            for (int j = 0; j < i; j++) {
                // 如果 dp[j] 为 true 且子字符串在字典中
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;  // 更新 dp[i] 为 true
                    break;         // 提前退出内层循环
                }
            }
        }
        
        // 返回整个字符串是否可以被拼接
        return dp[s.length()];
    }
}
