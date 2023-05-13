package a2022;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/8 22:02
 */
public class 搜索旋转排序数组 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(solution.search1(nums, 5));

    }

    static class Solution {
        /**
         * s202301.二分查找
         * 此题难点在于判断数组是否有序
         * @param nums
         * @param target
         * @return
         */
        public int search1(int[] nums, int target) {
            int len = nums.length;//数组的长度
            //先来判断两种特殊情况
            if (len == 0){return -1;}//空数组情况
            if (len == 1){ return nums[0]==target ? 0 : -1;}//数组长度为1的情况

            int left = 0, right = nums.length-1, res = -1;

            while (left <= right){
                int mid = left+(right-left)/2;

                if (nums[mid] == target){
                    return mid;
                }

                if (nums[0] <= nums[mid]){//mid左侧为升序排列
                    if (nums[0] <= target && target < nums[mid]){
                        right = mid - 1;
                    }else {
                        left = mid + 1;
                    }
                }else { //mid右侧为升序
                    if (nums[mid] < target && target <= nums[nums.length-1]){//注意理解这里的nums.length-1
                        left = mid + 1;
                    }else {
                        right = mid - 1;
                    }
                }

            }

            return res;
        }
    }
}
