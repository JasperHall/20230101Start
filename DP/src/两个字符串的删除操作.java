/**
 * https://leetcode.cn/problems/delete-operation-for-two-strings/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/9 15:47
 */
public class 两个字符串的删除操作 {  // 583. 两个字符串的删除操作

    class Solution {
        /**
         * dp数组中存储需要删除的字符个数
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance(String word1, String word2) {

            int[][] dp = new int[word1.length()+1][word2.length()+1];

            for (int i=0; i<word1.length()+1; i++) dp[i][0] = i;
            for (int j=0; j<word2.length()+1; j++) dp[0][j] = j;

            // i从1开始
            for (int i=1; i<word1.length()+1; i++){
                for (int j=1; j<word2.length()+1; j++){
                    if (word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];  // 从左上方推导
                    } else {
                        //               同时删除word12
                        dp[i][j] = Math.min(dp[i-1][j-1]+2,
                                                // 只删除word1          只删除word2
                                                Math.min(dp[i-1][j]+1, dp[i][j-1]+1)
                                    );
                    }
                }
            }

            return dp[word1.length()][word2.length()];
        }

        /**
         *  dp数组中存储word1和word2最长相同子序列的长度
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance2(String word1, String word2) {
            int len1 = word1.length();
            int len2 = word2.length();
            int[][] dp = new int[len1+1][len2+1];

            for (int i=1; i<=len1; i++){
                for (int j=1; j<=len2; j++){
                    if (word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]+1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            return len1 + len2 - dp[len1][len2]*2;
        }
    }
}
