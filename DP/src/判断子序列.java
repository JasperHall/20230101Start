/**
 * https://leetcode.cn/problems/is-subsequence/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 13:56
 */
public class 判断子序列 {  // 392. 判断子序列


    class Solution {
        /**
         * 动态规划
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {
            int length1 = s.length();
            int length2 = t.length();

            int[][] dp = new int[length1+1][length2+1];

            for (int i=1; i<=length1; i++){  // 注意这里i从1开始, <=
                for (int j=1; j<=length2; j++){ // 注意这里j从1开始, <=
                    if (s.charAt(i-1) == t.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]+1;
                    } else {
                        dp[i][j] = dp[i][j-1];
                    }
                }
            }
            // length1是字符串s的长度
            if (dp[length1][length2] == length1){
                return true;
            } else {
                return false;
            }
        }

        /**
         * 双指针
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence2(String s, String t) {
            int m = s.length();
            int n = t.length();
            /*
            这样写不行, 这样就是只要有就行, 无法保证字符的顺序了,
            int res = 0;
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    if (s.charAt(i) == t.charAt(j)) res++;
                }
            }
            return res == m;
            */
            int i=0, j=0;
            while (i<m && j<n){
                if (s.charAt(i) == t.charAt(j)) i++;
                j++;
            }

            return i==m;

        }
    }
}
