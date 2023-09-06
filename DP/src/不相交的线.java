/**
 * https://leetcode.cn/problems/uncrossed-lines/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/6 11:19
 */
public class 不相交的线 {  // 1035. 不相交的线

    class Solution {
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[len1][len2];
        }
    }
}
