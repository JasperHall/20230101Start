import java.util.Scanner;

/**
 * https://leetcode.cn/problems/maximum-subarray/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/6 11:35
 */
public class 最大子数组和 {  // 53. 最大子数组和

    public static void main(String[] args) {
        int[] test = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int res = maxSubArray(test);
        System.out.println(res);
    }

    /**
     * 自写
     * 错误
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int temp = 0;
        for (int i=0; i<nums.length; i++){
            temp += nums[i];
            if (res > temp){
                temp=res;
            } else {
                res = temp;
            }
        }
        return res;
    }

    /**
     * 代码随想录
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // i=1 开始
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);

            res = Math.max(res, dp[i]);
        }
        return res;
    }

}