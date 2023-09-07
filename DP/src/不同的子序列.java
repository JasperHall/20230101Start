/**
 * https://leetcode.cn/problems/distinct-subsequences/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 15:09
 */
public class 不同的子序列 {

    class Solution {
        /**
         * 动态规划
         * @param s
         * @param t
         * @return
         */
        public int numDistinct(String s, String t) {
            int[][] dp = new int[s.length()+1][t.length()+1];

            for (int i=0; i<s.length()+1; i++){
                dp[i][0] = 1;  // 初始化
            }

            // 注意 i 和 j 的起始值和取值范围
            for (int i=1; i<s.length()+1; i++){
                for (int j=1; j<t.length()+1; j++){
                    if (s.charAt(i-1) == t.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            return dp[s.length()][t.length()];
        }
    }
}
