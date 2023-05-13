package a202302;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/28 16:13
 *
 *
 * https://leetcode.cn/problems/remove-element/
 */
public class 移除元素 {

    class Solution {
        /**
         * 遍历直接做??
         *
         * 直接遍历的话int值是对的,但是测试不通过,LeetCode有自己的测试方法
         * @param nums
         * @param val
         * @return
         */
        public int removeElement(int[] nums, int val) {
            int len = nums.length;
            int count = 0;
            for (int i=0; i<len; i++){
                if (nums[i]==val) count++;
            }
            return len-count;
        }

        /**
         * 暴力法
         *
         * 双层for循环
         * @param nums
         * @param val
         * @return
         */
        public int removeElement4(int[] nums, int val){
            int len = nums.length;
            for (int i=0; i<len; i++){
                if (nums[i] == val){
                    for (int j=i+1; j<len; j++){
                        nums[j-1] = nums[j];

                    }
                    i--;// 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
                    len--; // 此时数组的大小-1
                }
            }
            return len;
        }

        /**
         * 方法二:双指针法(快慢指针
         * @param nums
         * @param val
         * @return
         */
        public int removeElement2(int[] nums, int val) {
            int len = nums.length;
            int slow = 0;//左指针:慢指针
            for (int fast=0; fast<len; fast++){//fast右指针:快指针
                if (nums[fast] != val){
                    nums[slow] = nums[fast];
                    slow++;
                }
            }
            return slow;
        }

        /*
         * 方法二：双指针优化(前后指针
         *
         * 如果左指针指向的元素等于val，此时将右指针指向的元素复制到左指针的位置然后右指针左移一位。
         * 如果赋值过来的元素恰好也等于val，可以继续把右指针指向的元素的值赋值过来(左指针指向的等于的元素的位置继续被覆盖)，
         * 直到左指针指向的元素的值不等于val 为止。
         * 当左指针和右指针重合的时候，左右指针遍历完数组中所有的元素。
         * 这样的方法两个指针在最坏的情况下合起来只遍历了数组一次。与方法一不同的是，方法二避免了需要保留的元素的重复赋值操作
         * */
        public int removeElement3(int[] nums, int val){
            int left = 0;
            int right = nums.length;
            while (left<right){
                if (nums[left] == val){
                    nums[left] = nums[right-1];
                    right--;
                } else {
                    left++;
                }
            }
            return left;
        }
    }
}
