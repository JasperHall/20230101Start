/**
 * https://leetcode.cn/problems/house-robber/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 10:09
 */
public class 打家劫舍 {  // 198. 打家劫舍


    class Solution {
        /**
         * 经典动态规划
         * @param nums
         * @return
         */
        public int rob(int[] nums) {
            if (nums==null || nums.length==0) return 0;
            if (nums.length == 1) return nums[0];

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(dp[0], nums[1]);

            for (int i=2; i<nums.length; i++){
                dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
            }

            return dp[nums.length-1];
        }
    }


}
