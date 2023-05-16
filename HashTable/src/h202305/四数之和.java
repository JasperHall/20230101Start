package h202305;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/4sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 19:01
 */
public class 四数之和 {  // 18
    class Solution {
        /**
         * 双指针(头尾指针
         *
         * 此题的关键在于剪枝和去重
         * @param nums
         * @param target
         * @return
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);

            // 第一层 for 循环
            for (int i=0; i<nums.length; i++){
                // 如果最小值大于0则直接返回, 如果最小值大于目标值则直接返回. nums[i] > target 直接返回, 剪枝操作
                // 同时满足大于0,大于目标值是因为如果target是负数时, 和是最小的
                if (nums[i]>0 && nums[i]>target) return result;  // 剪枝

                if (i>0 && nums[i-1]==nums[i]) continue;  // 对最左边的i进行去重, 第一次去重

                // 第二层 for 循环, 控制第二个数, 接下来的第三,四个数用双指针实现
                for (int j=i+1; j< nums.length; j++){
                    if (j>i+1 && nums[j-1]==nums[j]) continue; // 对左边第二个数 nums[j] 进行去重,  第二次去重

                    // 这里left起始为 j+1
                    int left = j+1;
                    int right = nums.length-1;
                    while (right > left){
                        // nums[k] + nums[i] + nums[left] + nums[right] > target 用int会溢出
                        long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum > target) {
                            right--;
                        } else if (sum < target) {
                            left++;
                        } else {
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                            // 对nums[left]和nums[right]去重
                            while (right > left && nums[right] == nums[right - 1]) right--; // 第三次去重
                            while (right > left && nums[left] == nums[left + 1]) left++;  // 第四次去重

                            left++;
                            right--;
                        }
                    }
                }
            }
            return result;
        }
    }
}
