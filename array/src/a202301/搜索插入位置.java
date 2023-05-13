package a202301;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/25 14:18
 */
//https://leetcode.cn/problems/search-insert-position/
public class 搜索插入位置 {

    class Solution {
        public int searchInsert(int[] nums, int target) {
            if (target < nums[0]){
                return 0;
            }
            if (target > nums[nums.length-1]){
                return nums.length;
            }

            int l=0, r=nums.length-1;

            while (l <= r){
                int mid = l + (r-l)/2;
                if (nums[mid] == target){
                    return mid;
                }else if (nums[mid] > target){
                    r = mid-1;
                }else if (nums[mid] < target){
                    l = mid+1;
                }
            }
            return l;
        }
    }
}
