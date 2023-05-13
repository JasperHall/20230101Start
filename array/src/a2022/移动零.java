package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/4 9:07
 */

//https://leetcode.cn/problems/move-zeroes/
public class 移动零 {
    public static void main(String[] args) {
        int[] a= {1,3,0,5,0,3};
        System.out.println(Arrays.toString(moveZeroes1(a)));
    }

    //方法一：两次变量，双指针
    public static int[] moveZeroes1(int[] nums) {
        if (nums==null){
            return nums;
        }
        /**
         * j和i双指针，每当第i个元素不等于0时，就把该元素赋值给第j个元素
         */
        int j = 0;
        for (int i=0; i<nums.length; i++){
            if (nums[i] != 0){
                nums[j]=nums[i];
                j++;
            }
        }
        /**
         * 当不为0的元素移动完成后，将第j个元素后面的元素全部设置为0
         */
        for (int i=j; i<nums.length; i++){
            nums[i]=0;
        }
        return nums;

    }
    //方法二（优化）：一次遍历,不明显的双指针
    public static int[] moveZeroes2(int[] nums) {
        if(nums == null){
            return nums;
        }

        int j =0;
        for (int i=0; i<nums.length; i++){
            if (nums[i]!=0){//当目前所在的元素!=0，就把其交换到左边，等于0的交换到右边
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j++;
            }
        }
        return nums;
    }

}
