/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/11 20:44
 */
public class 使用最小花费爬楼梯 {  // 746. 使用最小花费爬楼梯
    class Solution {
        /**
         * 第一步不支付费用
         * @param cost
         * @return
         */
        public int minCostClimbingStairs(int[] cost) {
            int len = cost.length;
            int[] dp = new int[len+1];

            // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
            dp[0] = 0;
            dp[1] = 0;

            // 计算到达每一层台阶的最小费用
            for (int i=2; i<=len; i++){
                dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
            }

            return dp[len];
        }

        /**
         * 优化空间复杂度
         * @param cost
         * @return
         */
        public int minCostClimbingStairs2(int[] cost) {
            int dp0 = 0;
            int dp1 = 0;  // 注意 初始两个值都是 0
            for (int i=2; i<=cost.length; i++){
                int dpi = Math.min(dp1+cost[i-1], dp0+cost[i-2]);
                dp0 = dp1;
                dp1 = dpi;
            }
            return dp1;
        }
    }
}
