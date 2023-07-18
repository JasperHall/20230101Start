import java.util.Arrays;

/**
 * https://leetcode.cn/problems/target-sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/17 19:07
 */
public class 目标和 {  // 494. 目标和
    public static void main(String[] args) {
        int[] a = new int[]{1,1,1,1,1};
        int target = 3;
        Solution s = new Solution();
        int res = s.findTargetSumWays2(a, target);
    }

    static class Solution {
        /**
         * 一维数组
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 如果target过大 sum将无法满足
            if (target<0 && sum<-target) return 0;
            if ((target+sum) % 2 != 0) return 0;  // 注意理解这一步

            int size = (target+sum) / 2;
            if (size<0) size = -size;  // 负数转正数后进行计算

            int[] dp = new int[size+1];  // 注意这里要 +1
            dp[0] = 1;
            for (int i=0; i<nums.length; i++){
                for (int j=size; j>=nums[i]; j--){
                    dp[j] += dp[j-nums[i]];
                }
            }
            return dp[size];
        }

        /**
         * 二维数组
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays2(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 如果target过大 sum将无法满足
            if (target<0 && sum<-target) return 0;
            if ((target+sum) % 2 != 0) return 0;  // 注意理解这一步

            int size = (target+sum) / 2;

            int[][] dp = new int[nums.length+1][size+1];  // 注意这里的数组两个方向长度的选择
            dp[0][0] = 1;

            // 注意初始下标的选取
            for (int i=1; i<nums.length+1; i++){  // 下标0, 已经初始化了, 所以从1开始
                for (int j=0; j<=size; j++){

                    dp[i][j] = dp[i-1][j];  // 不选取当前元素
                    if (j >= nums[i-1]){  // 背包容量 大于 当前与元素体积
                        dp[i][j] += dp[i-1][j-nums[i-1]];  // 选取当前元素
                    }
                }
            }

            // 打印数组
            /*for (int i=0; i<nums.length+1; i++){
                for (int j=0; j<=size; j++){
                    System.out.print(dp[i][j] + "  ");
                }
                System.out.println();
            }
            System.out.println(dp[nums.length][size]);*/

            return dp[nums.length][size];
        }
    }

}
