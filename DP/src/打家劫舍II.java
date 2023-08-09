/**
 * https://leetcode.cn/problems/house-robber-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 10:36
 */
public class 打家劫舍II {  // 213. 打家劫舍 II
    class Solution {
        /**
         *
         * @param nums
         * @return
         */
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int len = nums.length;
            if (len == 1) return nums[0];

            int result1 = robAction(nums, 0, len - 1);  // 情况二
            int result2 = robAction(nums, 1, len);  // 情况三

            return Math.max(result1, result2);
        }
        // 198.打家劫舍的逻辑
        int robAction(int[] nums, int start, int end) {
            if(start == end) return nums[start];

            // 也可以用这个逻辑
            int x = 0, y = 0, z = 0;
            for (int i = start; i < end; i++) {
                y = z;
                z = Math.max(y, x + nums[i]);
                x = y;
            }
            return z;

        }

        /**
         * 使用数组
         * @param nums
         * @return
         */
        public int rob2(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int len = nums.length;
            if (len == 1) return nums[0];

            int result1 = robAction2(nums, 0, len - 2);  // 情况二
            int result2 = robAction2(nums, 1, len-1);  // 情况三

            return Math.max(result1, result2);
        }
        // 198.打家劫舍的逻辑
        int robAction2(int[] nums, int start, int end) {
            if(start == end) return nums[start];

            int[] dp = new int[nums.length];
            dp[start] = nums[start];
            dp[start+1] = Math.max(dp[start], nums[start+1]);
            for (int i=start+2; i<=end; i++){  // 注意这里范围的限制 i<=end
                dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
            }
            return dp[end];
        }
    }

}
