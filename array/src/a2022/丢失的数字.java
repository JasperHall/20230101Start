package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/3 15:46
 */
//https://leetcode.cn/problems/missing-number/
public class 丢失的数字 {
    public static void main(String[] args) {
        int[] a = new int[]{0,1};
        System.out.println(missingNumber(a));
    }

    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            if (nums[i] != i){
                return i;
            }
        }
        return nums.length;
    }

}
