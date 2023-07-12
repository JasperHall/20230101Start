/**
 * https://leetcode.cn/problems/unique-paths/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/11 22:05
 */
public class 不同路径 {  // 62. 不同路径
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];

            // 初始化
            for (int i = 0; i < m; i++) {
                dp[i][0] = 1;
            }
            for (int i = 0; i < n; i++) {
                dp[0][i] = 1;
            }

            for (int i=1; i<m; i++){
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }

            return dp[m-1][n-1]; // 注意这里最终坐标要长度-1
        }


        /**
         * 一维数组解法
         * @param m
         * @param n
         * @return
         */
        public int uniquePaths2(int m, int n) {
            int[] dp = new int[n];  // 这里长度为n

            for (int i=0; i<n; i++) dp[i] = 1;

            for (int j=1; j<m; j++){  // 外层循环为 <m
                for (int i=1; i<n; i++){  // 内层循环为 <n
                    dp[i] += dp[i-1];
                }
            }
            return dp[n-1];
        }
    }
}
