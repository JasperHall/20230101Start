package a202301;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/squares-of-a-sorted-array/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/25 19:30
 */
public class 有序数组的平方 {
    class Solution {
        /**
         * 方法一：暴力排序
         * 这个时间复杂度是 O(n + nlogn)， 可以说是O(nlogn)的时间复杂度，但为了和下面双指针法算法时间复杂度有鲜明对比，我记为 O(n + nlog n)
         * @param nums
         * @return
         */
        public int[] sortedSquares1(int[] nums) {
            for (int i=0; i<=nums.length-1; i++){
                nums[i] = nums[i] * nums[i];
            }
            Arrays.sort(nums);
            return nums;
        }

        /**
         * 方法二：双指针法
         * 时间复杂度为O(n)，相对于暴力排序的解法O(n + nlog n)还是提升不少的
         * @param nums
         * @return
         */
        public int[] sortedSquares2(int[] nums) {
            int right = nums.length-1;//右指针
            int left = 0; //左指针
            int[] result = new int[nums.length];//结果要返回的数组
            int index = result.length - 1; //控制result数组存入数值的指针

            while (left <= right){//注意这里要有等于，如果没有等于就可能会缺少值
                if (nums[left]*nums[left] > nums[right]*nums[right]){
                    result[index--] = nums[left]* nums[left];//注意这里的index--使用, 先计算完成后再减一
                    left++;
                }else {
                    result[index--] = nums[right]*nums[right];
                    right--;
                }
            }
            return result;
        }

    }
}
