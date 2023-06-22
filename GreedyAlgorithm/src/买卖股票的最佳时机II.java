/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/22 10:36
 */
public class 买卖股票的最佳时机II {  //122
    class Solution {
        /**
         * 贪心
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int result = 0;
            for (int i=1; i<prices.length; i++){  // 注意这里的遍历是从 1 开始, 因为利润是后一天减前一天
                result += Math.max(prices[i]-prices[i-1], 0);
            }
            return result;
        }
    }
}
