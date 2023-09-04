/**
 * https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/4 18:23
 */
public class 最长连续递增序列 {  // 674. 最长连续递增序列

    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            int dp[] = new int[nums.length];

            for (int i=0; i<dp.length; i++){
                dp[i] = 1;
            }
            int res = 1; // 返回值初始化为1

            // 这里写i从0开始, 后面比较i+1和i的位置; 如果写i从1开始, 比较i-1和i的位置
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i+1] > nums[i]){
                    dp[i+1] = dp[i]+1;
                }
                res = Math.max(res, dp[i + 1]);
            }
            return res;
        }
    }
}
