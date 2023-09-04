import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/4 17:16
 */
public class 最长递增子序列 {   // 300. 最长递增子序列
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int res = 1;  // 这地方要写1, 让输入长度为1的数组时会直接返回长度1
            Arrays.fill(dp, 1);  // 最小递增子序列长度为1, 先都填充上1

            for (int i=1; i<dp.length; i++){
                for (int j=0; j<i; j++){
                    if (nums[i] > nums[j]){
                        // 如果当前i所在的元素值比j所在的元素值大, 则进入判断内部, 比较dp[i]的值和前一个动态数组的j位置的值+1的大小
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                    res = Math.max(res, dp[i]);
                }
            }
            return res;
        }
    }
}
