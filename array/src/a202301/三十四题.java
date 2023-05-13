package a202301;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/11 0:00
 */
public class 三十四题 {

    class Solution {
        /**
         * 题解一
         * @param nums
         * @param target
         * @return
         */
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) return new int[]{-1,-1};

            int l=0, r=nums.length-1;//二分范围
            //查找元素的开始位置
            while (l < r){
                int mid = (l+r)/2;
                if (nums[mid] >= target){
                    r = mid;
                }else {
                    l = mid+1;
                }
            }
            if (nums[r] != target) return new int[]{-1,-1};//查找失败
            int L = r;
            l = 0 ;
            r = nums.length-1;
            //查找元素的结束位置
            while (l<r){
                int mid = (l + r + 1)/2;
                if(nums[mid] <= target ){//注意这里小于等于的的细节，上边那个没有等于号
                    l = mid;
                } else{
                    r = mid - 1;
                }
            }

            return new int[]{L,r};
        }
    }
}
