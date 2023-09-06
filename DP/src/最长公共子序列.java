/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/6 10:11
 */
public class 最长公共子序列 {  // 1143. 最长公共子序列

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            // char[] char1 = text1.toCharArray();
            // char[] char2 = text2.toCharArray();
            // 可以在一开始的时候就先把text1, text2 转成char[]，之后就不需要有这么多为了处理字符串的调整就可以和卡哥的code更一致

            int[][] dp = new int[text1.length()+1][text2.length()+1]; // // 先对dp数组做初始化操作

            for (int i = 1; i < text1.length()+1; i++) {  // i=1开始
                char char1 = text1.charAt(i-1);
                for (int j=1; j<text2.length()+1; j++){
                    char char2 = text2.charAt(j-1);

                    // 开始列出状态转移方程
                    if (char1 == char2){  // String与String值相等比较用equals()方法，char与char值相等比较用”==”进行比较。
                        dp[i][j] = dp[i-1][j-1]+1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[text1.length()][text2.length()];
        }
    }

}
