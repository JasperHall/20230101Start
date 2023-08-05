/**
 * https://leetcode.cn/problems/coin-change-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/5 20:34
 */
public class 零钱兑换II {

    class Solution {
        /**
         * 一维数组
         * @param amount
         * @param coins
         * @return
         */
        public int change(int amount, int[] coins) {
            // 递推表达式
            int[] dp = new int[amount+1];
            // 初始化dp数组, 表示金额为0时只有一种情况，也就是什么都不装
            dp[0] = 1;

            for (int i=0; i<coins.length; i++){  // 一维数组时必须先遍历硬币(物品)
                for (int j=coins[i]; j<=amount; j++){ // 遍历背包容量, 容量从硬币大小为起点省去判断是否能往里放
                    dp[j] += dp[j - coins[i]];
                }
            }
            return dp[amount];
        }

        /**
         * 二维数组情况
         * 方便理解
         * @param amount
         * @param coins
         * @return
         */
        public int change2(int amount, int[] coins) {
            int[][] dp = new int[coins.length][amount+1];  // 注意这里初始化的长度

            // 只有一种硬币的情况
            for (int i=0; i<amount; i+=coins[0]){ // 第0种硬币, 填满第0种硬币的整数倍的背包的方案只有一种
                dp[0][i] = 1;
            }

            for (int i=1; i<coins.length; i++){  // 遍历物品
                for (int j=0; j<=amount; j++){  // 遍历背包容量
                    // 第i种硬币使用0~k次，求和
                    for (int k=0; k*coins[i] <= j; k++){
                        dp[i][j] += dp[i - 1][j - k * coins[i]];  // 注意对这一步求和的理解
                    }
                }
            }
            return dp[coins.length-1][amount];
        }
    }
}
