/**
 * https://leetcode.cn/problems/combination-sum-iv/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/5 22:31
 */
public class 组合总和Ⅳ {

    class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target+1];  // 注意这里初始化的长度
            dp[0] = 1; // 初始化为1, 若为0的话后面的没法根据前面的递推

            for (int i=0; i<=target; i++){  // 遍历背包
                for (int j=0; j<nums.length; j++){ // 遍历物品
                    if (i >= nums[j]) dp[i] += dp[i-nums[j]];
                }
            }
            return dp[target];
        }
    }
}
