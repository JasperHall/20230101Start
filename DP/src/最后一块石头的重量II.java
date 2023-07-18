/**
 * https://leetcode.cn/problems/last-stone-weight-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/17 17:17
 */
public class 最后一块石头的重量II {  // 1049. 最后一块石头的重量 II
    class Solution {
        /**
         * 一维数组
         * @param stones
         * @return
         */
        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int i : stones) {
                sum += i;
            }

            int target = sum / 2;

            // 初始化dp数组
            int[] dp = new int[target+1];
            // 注意这里i要从0开始
            for (int i=0; i<stones.length; i++){    // 使用一维数组时, 物品遍历在外侧, 背包遍历在内测
                //采用倒序
                for (int j=target; j>=stones[i]; j--){
                    //两种情况，要么放，要么不放
                    dp[j] = Math.max(dp[j], dp[j-stones[i]]+stones[i]);
                }
            }
            return sum - 2*dp[target];
        }

        /**
         * 二维数组
         * @param stones
         * @return
         */
        public int lastStoneWeightII2(int[] stones) {
            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }
            int target = sum/2;

            int[][] dp = new int[stones.length][target+1];

            // 初始化背包放入下标为0的物品时,背包的最大价值(第0行的所有列的初始化)
            for (int j=stones[0]; j<=target; j++){  // 注意这里j的初始值
                dp[0][j] = stones[0];
            }

            for (int i=1; i<stones.length; i++){
                for (int j=1; j<=target; j++){
                    if (j >= stones[i]){  // 注意这里是 >=
                        //           不放:dp[i - 1][j]   放:dp[i - 1][j - stones[i]] + stones[i]
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-stones[i]]+stones[i]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            return sum - dp[stones.length-1][target] - dp[stones.length-1][target];
        }
    }
}
