/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/10 10:24
 */
public class 买卖股票的最佳时机 {  // 121. 买卖股票的最佳时机

    class Solution {
        public int maxProfit(int[] prices) {
            if(prices==null || prices.length==0) return 0;

            int length = prices.length;

            // dp[i][0]代表第i天持有股票的最大收益
            // dp[i][1]代表第i天不持有股票的最大收益
            int[][] dp = new int[length][2];
            int result = 0;
            dp[0][0] = -prices[0];  // 买了索引0处的股票
            dp[0][1] = 0;  // 没买股票

            for (int i = 1; i < length; i++) {
                dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]+prices[i]); // 前一天也没股票, 和 , 前一天有股票今天卖出去
            }
            return dp[length-1][1];
        }
    }
}
