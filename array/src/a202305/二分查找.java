package a202305;

/**
 * https://leetcode.cn/problems/binary-search/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/14 9:50
 */
public class 二分查找 {  // 704

    class Solution {
        /**
         * 二分查找
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length-1;
            while (left <= right){
                int mid = (right-left)/2 + left;
                int num = nums[mid];
                if (num == target){
                    return mid;
                } else if (num > target){
                    right = mid -1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }
    }
}
