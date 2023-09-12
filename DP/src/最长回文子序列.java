/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/12 12:07
 */
public class 最长回文子序列 {  // 516. 最长回文子序列

    class Solution {
        public int longestPalindromeSubseq(String s) {
            int len  = s.length();
            int[][] dp = new int[len+1][len+1];

            for (int i=len-1; i>=0; i--){   // 从后往前遍历 保证情况不漏
                dp[i][i] = 1; // 初始化  注意这里的坐标是 i,i
                for (int j = i+1; j < len; j++) {
                    if (s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], Math.max(dp[i][j], dp[i][j-1]));
                    }
                }
            }
            return dp[0][len-1];
        }
    }
}
