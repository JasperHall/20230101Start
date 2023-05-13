package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/22 18:00
 */
public class 基于排列构建数组 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {0,2,1,5,3,4};
//        a = [0,2,1,5,3,4];

        System.out.println(Arrays.toString(solution.buildArray(a)));
    }
    static class Solution{
        public int[] buildArray(int[] nums) {
            int[] ans = new int[nums.length];
            for (int i=0; i<nums.length; i++){
                ans[i] = nums[nums[i]];
            }
            return ans;
        }
    }
}
