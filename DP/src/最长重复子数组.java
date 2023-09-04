/**
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/4 19:55
 */
public class 最长重复子数组 {  // 718. 最长重复子数组

    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int reslut = 0; // 返回值
            int[][] dp = new int[nums1.length+1][nums2.length+1];
            // 长度加一之后, 二维数组会比原来的数组多一格, 多出来的这一格初始化默认为0, 这样使用i-1和j-1推导的时候会方便
            // 相当于自动初始化了

            for (int i = 1; i < nums1.length+1; i++) {
                for (int j = 1; j<nums2.length+1; j++){
                    if (nums1[i-1] == nums2[j-1]){
                        dp[i][j] = dp[i-1][j-1] + 1;
                        reslut = Math.max(reslut, dp[i][j]);
                    }
                }
            }
            return reslut;
        }
    }
}
