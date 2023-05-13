package a202301;

/**
 * https://leetcode.cn/problems/binary-search/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/3 15:31
 */
public class 二分查找 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1,0,3,5,9,12,13};
        int target = 0;
        System.out.println(solution.search(nums, target));
    }
    static class Solution {
        /**
         * s202301.二分查找
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            int m=0, n=nums.length-1;//m左下标，n右下标

            while (m<=n){
                int mid = (m+n)/2;
                if (nums[mid]>target){
                    n = mid-1;
                }else if (nums[mid]<target){
                    m = mid+1;
                }else if (nums[mid]==target){
                   return mid;
                }
            }
            return -1;
        }
    }
}
