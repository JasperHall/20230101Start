package a202301;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/15 23:18
 */
//https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
public class 买卖股票的最佳时机II {//122题
    class Solution {
        /**
         * 动态规划1
         * @param prices
         * @return
         */
        public int maxProfit1(int[] prices) {
           int len = prices.length;
           if (len < 2){
               return 0;//只有一天或者零天的话，收入0
           }
           //0：持有现金
           // 1：持有股票
           // 状态转移：0-1-0-1-0-1-0
           int[][] dp = new int[len][2];

           dp[0][0] = 0;
           dp[0][1] = -prices[0];//如果持有股票，当前拥有的现金数是当天股价的相反数

           for (int i=1; i<len; i++){
               //这两行调换顺序也可以
               dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);//前一天持有现金到今天，和前一天持有股票今天卖出后剩余现金比较
               dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);//前一天持有股票价值，和前一天持有现金今天买股票后的持有价值比较
           }
           return dp[len-1][0];
        }

        /**
         * 动态规划，将状态数组分开设置
         * @param prices
         * @return
         */
        public int maxProfit2(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }

            // cash：持有现金
            // hold：持有股票
            // 状态数组
            // 状态转移：cash → hold → cash → hold → cash → hold → cash
            int[] cash = new int[len];
            int[] hold = new int[len];

            cash[0] = 0;
            hold[0] = -prices[0];

            for (int i = 1; i < len; i++) {
                // 这两行调换顺序也是可以的
                cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
                hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
            }
            return cash[len - 1];
        }

        /**
         * 动态规划
         * 考虑优化空间
         * 由于当前行只参考上一行，每一行就 2 个值，因此可以考虑使用「滚动变量」（「滚动数组」技巧）。
         */
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }

            // cash：持有现金
            // hold：持有股票
            // 状态转移：cash → hold → cash → hold → cash → hold → cash

            int cash = 0;
            int hold = -prices[0];

            int preCash = cash;
            int preHold = hold;
            for (int i = 1; i < len; i++) {
                cash = Math.max(preCash, preHold + prices[i]);
                hold = Math.max(preHold, preCash - prices[i]);

                preCash = cash;
                preHold = hold;
            }
            return cash;
        }
    }
}
