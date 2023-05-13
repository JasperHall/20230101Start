package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/15 19:05
 */
//https://leetcode.cn/problems/find-peak-element/
public class 寻找峰值 {
    class Solution{
        public int findPeakElement(int[] nums){
            int len = nums.length;

            if (len == 0) return 0;

            int left = 0, right = len-1;
            while (left < right){
                int mid = left + right + 1 >> 1;
                if (nums[mid] > nums[mid-1]){
                    left = mid;
                }else {
                    right = mid -1;
                }
            }
            return right;//因为
        }
    }
}
