package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/15 0:40
 */
//https://leetcode.cn/problems/largest-perimeter-triangle/
public class 三角形的最大周长 {

    public static void main(String[] args) {
        int [] a = new int[]{2,1,2};
        int [] b = new int[]{1,2,1};
        Solution s = new Solution();
        System.out.println(s.largestPerimeter1(a));
        System.out.println(s.largestPerimeter1(b));
    }

    static class Solution {
        public int largestPerimeter1(int[] nums) {
            Arrays.sort(nums);
            //倒着遍历数组,挨着判断能不能组成三角形
            for (int i=nums.length-1; i>=2; i--){//注意i最小要大于等于2
                if (nums[i-1]+nums[i-2] > nums[i]){
                    return nums[i-1]+nums[i-2]+nums[i];
                }
            }
            return 0;
        }
    }
}
