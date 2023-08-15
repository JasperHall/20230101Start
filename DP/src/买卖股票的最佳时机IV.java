/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/15 17:11
 */
public class 买卖股票的最佳时机IV {  // 188. 买卖股票的最佳时机 IV

    class Solution {
        public int maxProfit(int k, int[] prices) {
            if (prices.length == 0) return 0;

            // [天数][股票状态]
            // 股票状态: 奇数表示第 k 次交易持有/买入, 偶数表示第 k 次交易不持有/卖出, 0 表示没有操作
            int len = prices.length;
            int[][] dp = new int[len][k*2 + 1];

            // dp数组的初始化, 与版本一同理
            for (int i = 1; i < k*2; i += 2) {  // 保证i都是奇数
                dp[0][i] = -prices[0];
            }

            for (int i = 1; i < len; i++) {
                for (int j = 0; j < k*2 - 1; j += 2) {
                    dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                    dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
                }
            }
            return dp[len - 1][k*2];
        }
    }

}
