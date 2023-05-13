package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/18 10:15
 */
//https://leetcode.cn/leetbook/read/array-and-string/c0w4r/
public class 长度最小的子数组 {
    public static void main(String[] args) {
        int [] a = new int[]{2,2,4,3,2,1,3};
        int n = 7;
        Solution solution = new Solution();
        int res = solution.minSubArrayLen2(n,a);
        System.out.println(res);
    }

    static class Solution {
        /**
         * 方法一：暴力双层for循环遍历，java超时
         * @param target
         * @param nums
         * @return
         */
        public int minSubArrayLen1(int target, int[] nums) {
            int length = nums.length;
            int min = Integer.MAX_VALUE;
            for (int i=0; i<length; i++){
                int sum=0;
                for (int j=i; j<length; j++){
                    sum += nums[j];
                    if (sum >= target){
                        min = Math.min(min, j-i+1);
                        break;
                    }
                }
            }
            return min == Integer.MAX_VALUE ? 0 :min; //如果min == Integer.MAX_VALUE返回0，否则返回min
        }

        /**
         * 前缀和+s202301.二分查找
         * @param target
         * @param nums
         * @return
         */
        public int minSubArrayLen2(int target, int[] nums) {
            int length = nums.length;

            if (length == 0){
                return 0;
            }
            //前缀和数组
            int[] sums = new int[length+1];
            sums[0] = 0;
            for (int i=1; i<=length; i++){
                sums[i] = sums[i-1]+nums[i-1];
            }

            int min = Integer.MAX_VALUE;//取得Integer的最大值
            for (int i=1; i<=length; i++){
                int tmp = target + sums[i-1];
                int pos = Arrays.binarySearch(sums, tmp);
                if (pos < 0){
                    pos =-pos - 1;
                }
                if (pos <= length){
                    min = Math.min(min, pos - (i-1));
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        /**
         * 滑动窗口，本质也是双指针
         * @param target
         * @param nums
         * @return
         */
        public int minSubArrayLen3(int target, int[] nums) {
            // 滑动窗口，首先定义一个左指针，一个右指针，一个sum存储计算的和
            int left = 0, right = 0, sum = 0;
            int min = Integer.MAX_VALUE;
            while(right < nums.length){
                sum += nums[right];
                while(sum >= target){
                    min = Math.min(min, right - left + 1);
                    sum -= nums[left];
                    left++;
                }
                right++;
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        /**
         * 前缀和+双指针
         * 基本思路:
         * 前缀和记录前N个元素之和已备后续计算子数组和，再过滤掉特殊情况，快慢指针遍历前缀和即可，其中快慢指针移动规则如下:
         *
         * 1.慢指针和快指针所夹区间（前开后闭）等于目标值，则将区间大小于结果集对比并更替结果集；
         * 2.慢指针和快指针所夹区间（前开后闭）小于目标值，则将区间大小于结果集对比并更替结果集并前移快指针；
         * 3.慢指针和快指针所夹区间（前开后闭）大于目标值，则前移慢指针
         */
        public int minSubArrayLen4(int target, int[] nums) {
            int len = nums.length;
            //前缀和
            int[] prefixSum = new int[len+1];
            for (int idx=0; idx<len; idx++){
                prefixSum[idx+1] = prefixSum[idx] + nums[idx];
            }
            //目标大于全数组数据之和
            if (prefixSum[len] < target) return 0;
            //目标恰好等于全数组数据之和
            if (prefixSum[len] == target) return len;

            int slow=0, fast=0, res=Integer.MAX_VALUE;
            while (fast <= len){
                if (prefixSum[fast] - prefixSum[slow] <target){
                    fast++;
                }else if (prefixSum[fast] - prefixSum[slow] > target){
                    res = Math.min(res, fast-slow);
                    slow++;
                }else {
                    res = Math.min(res, fast-slow);
                    fast++;
                    slow++;
                }
            }
            return res == Integer.MAX_VALUE ? 0 :res;
        }

    }
}
