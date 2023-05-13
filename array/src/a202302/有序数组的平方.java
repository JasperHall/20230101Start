package a202302;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/28 16:40
 *
 * https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class 有序数组的平方 {

    class Solution {
        /**
         * 双指针法: 左右指针
         *
         * 该方法是的使用是考虑到了本题为非递减排序的数组
         * @param nums
         * @return
         */
        public int[] sortedSquares(int[] nums) {
            int left = 0;//左指针
            int right = nums.length-1;//右指针
            int res[] = new int[nums.length];//结果数组
            int index = nums.length-1;//用来控制结果数组的下标

            while (left <= right){//注意这里的 <=, 不加 = 号的加可能会缺少值
                if (nums[left]*nums[left] <= nums[right]*nums[right]){

                    res[index--] = nums[left]* nums[left];//注意这里的index--使用, 先计算完成后再减一

                    /*//可以用下面两行替换上面一行

                    res[index] = nums[right]*nums[right];//index是从大到小移动的
                    index--;
                    */

                    right--;
                }else {
                    res[index] = nums[left]*nums[left];
                    index--;
                    left++;
                }

            }
            return res;

        }
    }
}
