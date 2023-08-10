/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/10 11:06
 */
public class 买卖股票的最佳时机II {  // 122. 买卖股票的最佳时机 II
    class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];     // 创建二维数组存储状态
            // 初始状态
            dp[0][0] = -prices[0];  // 第一天就持有
            dp[0][1] = 0;  // 第一天不持有
            for (int i = 1; i < n; i++) {
                // 第 i 天，有股票, 第i-1天持有股票所得现金 和 i-1天没有股票i天买来股票所得现金是昨天不持有股票的所得现金 减去 今天的股票价格
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
                // 第 i 天，没有股票, i-1天没有股票所持现金 和 i-1天有股票i天卖出后所得现金 取最大
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            }
            return dp[n - 1][1];    // 卖出股票收益高于持有股票收益，因此取[1]
        }
    }
}
